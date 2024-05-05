package cn.zrylhh.tesseractWeb.utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * 项目名：TesseractWeb.
 * 文件名：
 * 模块说明：
 * 修改历史：
 * 2021-05-14 - ZhaoLongTao - 创建.
 */
public class OCRUtil {
    public static String parse(String filePath, String resourcePath) throws TesseractException {

        File imageFile = new File(filePath);

        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        instance.setDatapath(resourcePath);
        instance.setLanguage("chi_sim");//添加中文字库
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
//         File tessDataFolder = LoadLibs.extractTessResources("tessdata"); // Maven build bundles English data
//         instance.setDatapath(tessDataFolder.getPath());

        String result = instance.doOCR(imageFile);
        System.out.println(result);

        return result;
    }
}
