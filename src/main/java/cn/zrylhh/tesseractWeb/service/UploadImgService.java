package cn.zrylhh.tesseractWeb.service;

import cn.zrylhh.tesseractWeb.model.ResponseResult;
import cn.zrylhh.tesseractWeb.model.UpdateTextReq;
import org.springframework.web.multipart.MultipartFile;

/**
 * 项目名：TesseractWeb.
 * 文件名：
 * 模块说明：
 * 修改历史：
 * 2021-05-14 - ZhaoLongTao - 创建.
 */
public interface UploadImgService {

    public ResponseResult procUploadImg(MultipartFile file,String realPath);
    public ResponseResult updateText(UpdateTextReq reqDto);
    public ResponseResult searchByText(String ocrText);
    public ResponseResult getRandomImg();
    public ResponseResult searchById(String md5Id);
}
