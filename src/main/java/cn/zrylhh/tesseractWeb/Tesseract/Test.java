package cn.zrylhh.tesseractWeb.Tesseract;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 项目名：TesseractWeb.
 * 文件名：
 * 模块说明：
 * 修改历史：
 * 2021-03-12 - ZhaoLongTao - 创建.
 */
public class Test {

    private static Logger logger = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args) {
        // System.setProperty("jna.library.path", "32".equals(System.getProperty("sun.arch.data.model")) ? "lib/win32-x86" : "lib/win32-x86-64");
        // p5 p7 p8 p10
        File imageFile = new File("E:\\projects\\TesseractWeb\\src\\main\\resources\\train_material\\p19.jpg");

        logger.info("the file is {}",imageFile.getAbsolutePath());

        File resourceDir = new File(Test.class.getClassLoader().getResource("").getPath());

        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        instance.setDatapath(resourceDir.getPath() + "\\tessdata");
        instance.setLanguage("chi_sim");//添加中文字库
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
//         File tessDataFolder = LoadLibs.extractTessResources("tessdata"); // Maven build bundles English data
//         instance.setDatapath(tessDataFolder.getPath());

        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            e.printStackTrace();
        }

    }
}
