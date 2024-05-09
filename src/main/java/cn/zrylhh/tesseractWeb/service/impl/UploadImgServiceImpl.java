package cn.zrylhh.tesseractWeb.service.impl;

import cn.zrylhh.tesseractWeb.dao.ImgRecordDao;
import cn.zrylhh.tesseractWeb.model.ImgRecord;
import cn.zrylhh.tesseractWeb.model.ResponseResult;
import cn.zrylhh.tesseractWeb.model.UpdateTextReq;
import cn.zrylhh.tesseractWeb.service.UploadImgService;
import cn.zrylhh.tesseractWeb.utils.FileUtil;
import cn.zrylhh.tesseractWeb.utils.OCRUtil;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名：TesseractWeb.
 * 文件名：
 * 模块说明：
 * 修改历史：
 * 2021-05-14 - ZhaoLongTao - 创建.
 */
@Service
public class UploadImgServiceImpl implements UploadImgService {


    private Logger logger = LoggerFactory.getLogger(UploadImgServiceImpl.class);
    @Autowired
    ImgRecordDao imgRecordDao;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Tika tika = new Tika();

    @Value("${static.tesseract}")
    private String tesseractResourcePath;

    @Override
    public ResponseResult procUploadImg(MultipartFile file,String uploadPath) {

        ResponseResult result = new ResponseResult();
        String parseResult = "";
        try {
            // 计算出图片的md5值
            String md5Info = DigestUtils.md5DigestAsHex(file.getBytes());
            // 通过md5值查询是否已有记录
            ImgRecord recordInDb = imgRecordDao.select(md5Info);
            if(recordInDb != null){
                // 数据库已有记录
                logger.info("already has {}",md5Info+recordInDb.getFileSuffix());
                result.setStatus("ok");
                Map<String,Object> data = new HashMap<>();
                data.put("img_text",recordInDb.getOcrText());
                data.put("img_url","/up/"+recordInDb.getMd5Name()+recordInDb.getFileSuffix());
                data.put("img_md5",md5Info);
                result.setData(data);
            }else{
                // 新图片，尚无记录

                logger.info("new file, will upload to {}",uploadPath);
                // 上传文件
                File targetFile = FileUtil.uploadFile(file, uploadPath);
                // 判断文件的格式，如果是动图则不做文字识别
                FileInputStream fileInputStream = new FileInputStream(targetFile);
                String detect = tika.detect(targetFile);

                String[] typeArr = detect.split("/");
                String mainType = typeArr[0];
                String subType = typeArr[1];
                if(!mainType.equalsIgnoreCase("image")){
                    logger.info("wrong type! ",targetFile.getAbsolutePath());

                    result.setStatus("ok");
                    Map<String,Object> data = new HashMap<>();
                    data.put("img_text","错误的文件类型");
                    data.put("img_url","/up/");
                    data.put("img_md5","");
                    result.setData(data);
                    return result;
                }

                // 对于动图不做文字识别
                if(!subType.equalsIgnoreCase("gif")){
                    // 解析文字 传入语言文件的路径
                    parseResult = OCRUtil.parse(targetFile.getPath(),tesseractResourcePath);
                }


                ImgRecord record = new ImgRecord();
                record.setFilePath(targetFile.getPath());
                // tika识别的类型不一定与文件后缀相同，例如.jpg文件识别类型为jpeg
                record.setFileSuffix(targetFile.getName().substring(targetFile.getName().lastIndexOf(".")));
                LocalDateTime now = LocalDateTime.now();
                String lsUpTime = dateTimeFormatter.format(now);
                record.setLsUpTime(lsUpTime);
                record.setMd5Name(md5Info);
                // TODO 后续进行分词
                // 进行清理，替换空格或者其他
                parseResult = parseResult.replace(" ","");
                record.setOcrText(parseResult.trim());
                record.setOriginalName(file.getOriginalFilename());
                record.setUploadIp("127.0.0.1");

                imgRecordDao.insert(record);

                result.setStatus("ok");
                Map<String,Object> data = new HashMap<>();
                data.put("img_text",parseResult);
                data.put("img_url","/up/"+targetFile.getName());
                data.put("img_md5",md5Info);
                result.setData(data);
            }

        } catch (FileNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ResponseResult updateText(UpdateTextReq reqDto) {
        // 仅插入一条新的记录到
        int i = imgRecordDao.updateText(reqDto);
        ResponseResult result = new ResponseResult();
        result.setStatus("ok");
        Map dataMap = new HashMap();
        dataMap.put("message","谢谢贡献");
        return null;
    }

    @Override
    public ResponseResult searchByText(String ocrText) {
        List<ImgRecord> imgRecords = imgRecordDao.selectByOrcText(ocrText);
        ResponseResult result = new ResponseResult();
        result.setStatus("ok");
        Map dataMap = new HashMap();

        List<Map<String,String>> imgs = new ArrayList<>();

        imgRecords.forEach(imgRecord -> {
            Map<String,String> img = new HashMap<>();
            img.put("img_text",imgRecord.getOcrText());
            if(imgRecord.getFileSuffix().startsWith(".")){
                img.put("img_url","/up/"+imgRecord.getMd5Name()+imgRecord.getFileSuffix());
            }else{
                img.put("img_url","/up/"+imgRecord.getMd5Name()+"."+imgRecord.getFileSuffix());
            }
            img.put("img_md5",imgRecord.getMd5Name());
            imgs.add(img);
        });
        dataMap.put("imgs",imgs);

        result.setData(dataMap);
        return result;
    }

    @Override
    public ResponseResult getRandomImg() {
        List<ImgRecord> imgRecords = imgRecordDao.getRandomImg(10);
        ResponseResult result = new ResponseResult();
        result.setStatus("ok");
        Map dataMap = new HashMap();

        List<Map<String,String>> imgs = new ArrayList<>();

        imgRecords.forEach(imgRecord -> {
            Map<String,String> img = new HashMap<>();
            img.put("img_text",imgRecord.getOcrText());
            if(imgRecord.getFileSuffix().startsWith(".")){
                img.put("img_url","/up/"+imgRecord.getMd5Name()+imgRecord.getFileSuffix());
            }else{
                img.put("img_url","/up/"+imgRecord.getMd5Name()+"."+imgRecord.getFileSuffix());
            }
            img.put("img_md5",imgRecord.getMd5Name());
            imgs.add(img);
        });
        dataMap.put("imgs",imgs);

        result.setData(dataMap);
        return result;
    }

    public static void main(String[] args) throws IOException {

        Tika tika = new Tika();

        File file = new File("C:\\Users\\zrylhh\\Desktop\\img\\-9lddQqwdl-eqblXbZ2zT1kS8d-9q.gif");
        String detect = tika.detect(file);
        System.out.println(detect);
    }
}
