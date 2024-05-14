package cn.zrylhh.tesseractWeb.model;

/**
 * 项目名：TesseractWeb.
 * 文件名：
 * 模块说明：
 * 修改历史：
 * 2021-05-14 - ZhaoLongTao - 创建.
 */
public class ImgTag {

    private String md5Name;
    private String tag;

    public ImgTag() {
    }
    public ImgTag(String md5Name, String tag) {
        this.md5Name = md5Name;
        this.tag = tag;
    }

    public String getMd5Name() {
        return md5Name;
    }

    public void setMd5Name(String md5Name) {
        this.md5Name = md5Name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
