package cn.zrylhh.tesseractWeb.controller;

import cn.zrylhh.tesseractWeb.model.DirectoryChild;
import cn.zrylhh.tesseractWeb.model.GetDirContent;
import cn.zrylhh.tesseractWeb.utils.DirectoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.StandardServletEnvironment;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目名：TesseractWeb.
 * 文件名：
 * 模块说明：
 * 修改历史：
 * 2021-07-02 - ZhaoLongTao - 创建.
 */
@RestController
@RequestMapping("/gallery/")
public class DirectoryController {

    @Autowired
    private StandardServletEnvironment env;

    @Value("${gallery.baseDir}")
    private String baseDir;


    @RequestMapping(value="/getDirContent", method = RequestMethod.POST)
    public List<DirectoryChild> getDirContent(@RequestBody GetDirContent reqDto, HttpServletRequest request){

        List<DirectoryChild> allChild = DirectoryUtil.getAllChild(baseDir, reqDto.getRelativePath());

        return allChild;
    }

    @RequestMapping(value="/test", method = RequestMethod.POST)
    public List<DirectoryChild> getTest(@RequestBody GetDirContent reqDto, HttpServletRequest request){
        // 查看spring中到底有多少配置源，其中是否会有重复的配置项，以及不同配置源的优先级
        env.getPropertySources().forEach(propertySource -> {
            System.out.println(propertySource + " {} -> {}");
        });

//        List<DirectoryChild> allChild = DirectoryUtil.getAllChild(baseDir, reqDto.getRelativePath());

        return null;
    }

}
