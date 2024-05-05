package cn.zrylhh.tesseractWeb.utils;

import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 项目名：TesseractWeb.
 * 文件名：
 * 模块说明：
 * 修改历史：
 * 2021-05-13 - ZhaoLongTao - 创建.
 */
public class FileUtil {

    public static File uploadFile(MultipartFile file, String uploadPath) throws Exception {
        String md5Info = DigestUtils.md5DigestAsHex(file.getBytes());

        String originalFilename = file.getOriginalFilename();

        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        File targetFile = new File(uploadPath+md5Info+suffix);
//        if(!targetFile.exists()){
//            targetFile.mkdirs();
//        }
        FileOutputStream out = new FileOutputStream(targetFile.getPath());
        out.write(file.getBytes());
        out.flush();
        out.close();
        return targetFile;
    }

}
