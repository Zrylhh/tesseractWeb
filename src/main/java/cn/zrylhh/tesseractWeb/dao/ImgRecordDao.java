package cn.zrylhh.tesseractWeb.dao;

import cn.zrylhh.tesseractWeb.model.ImgRecord;
import cn.zrylhh.tesseractWeb.model.ImgTag;
import cn.zrylhh.tesseractWeb.model.UpdateTextReq;

import java.util.List;

/**
 * 项目名：TesseractWeb.
 * 文件名：
 * 模块说明：
 * 修改历史：
 * 2021-05-14 - ZhaoLongTao - 创建.
 */
public interface ImgRecordDao {

    public int insert(ImgRecord record);
    public ImgRecord select(String md5Name);
    public int updateText(UpdateTextReq reqDto);
    public List<ImgRecord> selectByOrcText(String octText);
    public List<ImgRecord> getRandomImg(Integer number);
    public List<ImgTag> getTagsById(String md5Id);
    public List<ImgRecord> selectByOrcText(String octText,Integer pages,Integer limit);
}
