package cn.zrylhh.tesseractWeb.utils;

import cn.zrylhh.tesseractWeb.model.DirectoryChild;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 项目名：TesseractWeb.
 * 文件名：
 * 模块说明：
 * 修改历史：
 * 2021-07-01 - ZhaoLongTao - 创建.
 */
public class DirectoryUtil {

    public enum FILETYPE {

        DIRECTORY,IMAGE,VIDEO,AUDIO,DOC
    }

    private enum SUFFIX{
        jpg,jpeg,png,gif,mp4,mkv,mp3
    }

    /**
     * 获取该目录下所有子节点
     * @param relativePath  相对路径
     * @param prePath       路径前缀，
     */
    public static List<DirectoryChild> getAllChild(String prePath,String relativePath){

        File currentNode = new File(prePath, relativePath);

        File[] files = currentNode.listFiles();
        List<DirectoryChild> childs = Arrays.stream(files).map(new Function<File, DirectoryChild>() {

            @Override
            public DirectoryChild apply(File file) {

                DirectoryChild directoryChild = new DirectoryChild();
                directoryChild.setPrePath(prePath);
                directoryChild.setRelativePath(relativePath);
                directoryChild.setDir(file.isDirectory());
                if (file.isDirectory()) {
                    directoryChild.setChildType(FILETYPE.DIRECTORY);
                } else {
                    // 截取后缀
                    String fileName= file.getName();
                    String suffix = fileName.substring(fileName.indexOf(".")).toLowerCase();
                    switch (suffix){
                        case "gif":
                        case "jpeg":
                        case "jpg":
                        case "png":
                            directoryChild.setChildType(FILETYPE.IMAGE);
                            break;
                        case "mp4":
                        case "mkv":
                            directoryChild.setChildType(FILETYPE.VIDEO);
                            break;
                        case "mp3":
                            directoryChild.setChildType(FILETYPE.AUDIO);
                            break;
                        default:
                            directoryChild.setChildType(FILETYPE.DOC);
                            break;
                    }
                }
                directoryChild.setChildName(file.getName());
                return directoryChild;
            }
        }).collect(Collectors.toList());
        return childs;
    }


    public static void main(String[] args) {
//        DirectoryUtil.getAllChild("E:\\Program Files\\apache-tomcat-10.0.7","webapps");

        SUFFIX jpg = SUFFIX.valueOf("jpggg");
        System.out.println(jpg);
    }

}
