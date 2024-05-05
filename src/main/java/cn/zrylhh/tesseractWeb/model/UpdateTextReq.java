package cn.zrylhh.tesseractWeb.model;

/**
 * 项目名：TesseractWeb.
 * 文件名：
 * 模块说明：
 * 修改历史：
 * 2021-05-27 - ZhaoLongTao - 创建.
 */
public class UpdateTextReq {

    private String imgMd5;
    private String updateText;

    public String getImgMd5() {
        return imgMd5;
    }

    public void setImgMd5(String imgMd5) {
        this.imgMd5 = imgMd5;
    }

    public String getUpdateText() {
        return updateText;
    }

    public void setUpdateText(String updateText) {
        this.updateText = updateText;
    }
}
