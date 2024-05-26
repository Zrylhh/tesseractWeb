package cn.zrylhh.tesseractWeb.dao.impl;

import cn.zrylhh.tesseractWeb.dao.ImgRecordDao;
import cn.zrylhh.tesseractWeb.model.ImgRecord;
import cn.zrylhh.tesseractWeb.model.ImgTag;
import cn.zrylhh.tesseractWeb.model.UpdateTextReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 项目名：TesseractWeb.
 * 文件名：
 * 模块说明：
 * 修改历史：
 * 2021-05-14 - ZhaoLongTao - 创建.
 */
@Repository
public class ImgRecordDaoImpl implements ImgRecordDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;  //这个是系统自带的

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public int insert(ImgRecord record) {
        String sql = "insert into img_record(`md5_name`,`original_name`,`ocr_text`,`lsup_time`,`upload_ip`,`file_path`,`file_suffix`) " +
                "values(?,?,?,?,?,?,?)";

        PreparedStatementCreator creator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1,record.getMd5Name());
                preparedStatement.setString(2,record.getOriginalName());
                preparedStatement.setString(3,record.getOcrText());
                preparedStatement.setString(4,record.getLsUpTime());
                preparedStatement.setString(5,record.getUploadIp());
                preparedStatement.setString(6,record.getFilePath());
                preparedStatement.setString(7,record.getFileSuffix());

                return preparedStatement;
            }
        };
        int update = jdbcTemplate.update(creator);
        return update;
    }

    @Override
    public ImgRecord select(String md5Name) {
//        md5_name|original_name|ocr_text|lsup_time|upload_ip|file_path|
        String sql = "select md5_name,original_name,ocr_text,lsup_time,upload_ip,file_path ,file_suffix " +
                " from img_record where md5_name = ?";
        try {
            ImgRecord imgRecord = jdbcTemplate.queryForObject(sql, new RowMapper<ImgRecord>() {
                @Override
                public ImgRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ImgRecord result = new ImgRecord();
                    result.setMd5Name(rs.getString("md5_name"));
                    result.setOriginalName(rs.getString("original_name"));
                    result.setOcrText(rs.getString("ocr_text"));
                    result.setLsUpTime(rs.getString("lsup_time"));
                    result.setUploadIp(rs.getString("upload_ip"));
                    result.setFilePath(rs.getString("file_path"));
                    result.setFileSuffix(rs.getString("file_suffix"));
                    return result;
                }
            },md5Name);

            return imgRecord;
        }catch (EmptyResultDataAccessException e){
            return null ;
        }
    }

    @Override
    public int updateText(UpdateTextReq reqDto) {
        // 插入一条新的记录到
        String sql = "insert into img_record_update(`md5_name`,`lsup_time`,`upload_ip`,`update_ocr_text`) " +
                "values(?,?,?,?)";

        PreparedStatementCreator creator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1,reqDto.getImgMd5());
                preparedStatement.setString(2,dateTimeFormatter.format(LocalDateTime.now()));
                preparedStatement.setString(3,"");
                //TODO 部分关键字的替换
                // 对文本进行清洗
                preparedStatement.setString(4,reqDto.getUpdateText().replace(" ",""));
                return preparedStatement;
            }
        };
        int update = jdbcTemplate.update(creator);
        return update;

    }

    @Override
    public List<ImgRecord> getRandomImg(Integer number) {

        String sql =  " select * from img_record ir order by random() limit  " +number ;
        try {

            PreparedStatementCreator creator = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    return preparedStatement;
                }
            };
            // TODO 附带查询出相关的标签
            List<ImgRecord> resultSet = jdbcTemplate.query(creator, new RowMapper<ImgRecord>() {
                @Override
                public ImgRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ImgRecord result = new ImgRecord();
                    result.setMd5Name(rs.getString("md5_name"));
                    result.setOriginalName(rs.getString("original_name"));
                    result.setOcrText(rs.getString("ocr_text"));
                    result.setLsUpTime(rs.getString("lsup_time"));
                    result.setUploadIp(rs.getString("upload_ip"));
                    result.setFilePath(rs.getString("file_path"));
                    result.setFileSuffix(rs.getString("file_suffix"));
                    return result;
                }
            });
            return resultSet;
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return null ;
        }

    }

    @Override
    public List<ImgRecord> selectByOrcText(String octText) {

        String sql =  "select a.* " +
                "from  " +
                "img_record a  " +
                "join  " +
                "( " +
                "   select md5_name from img_record  " +
                "   where ocr_text like ? " +
                "   union  " +
                "   select md5_name from img_record_update  " +
                "   where update_ocr_text like ? " +
                ") b on a.md5_name = b.md5_name  ";
        try {

            PreparedStatementCreator creator = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1,"%"+octText+"%");
                    preparedStatement.setString(2,"%"+octText+"%");
                    return preparedStatement;
                }
            };
            List<ImgRecord> resultSet = jdbcTemplate.query(creator, new RowMapper<ImgRecord>() {
                @Override
                public ImgRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ImgRecord result = new ImgRecord();
                    result.setMd5Name(rs.getString("md5_name"));
                    result.setOriginalName(rs.getString("original_name"));
                    result.setOcrText(rs.getString("ocr_text"));
                    result.setLsUpTime(rs.getString("lsup_time"));
                    result.setUploadIp(rs.getString("upload_ip"));
                    result.setFilePath(rs.getString("file_path"));
                    result.setFileSuffix(rs.getString("file_suffix"));
                    return result;
                }
            });
            return resultSet;
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return null ;
        }
    }

    @Override
    public List<ImgTag> getTagsById(String md5Id) {
        String sql = "select md5_name,update_ocr_text " +
                " from img_record_update where md5_name = ? ";
        try {
            List<ImgTag> resultSet = jdbcTemplate.query(sql, new RowMapper<ImgTag>() {
                @Override
                public ImgTag mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ImgTag result = new ImgTag();
                    result.setMd5Name(rs.getString("md5_name"));
                    result.setTag(rs.getString("update_ocr_text"));
                    return result;
                }
            },md5Id);

            return resultSet;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

}
