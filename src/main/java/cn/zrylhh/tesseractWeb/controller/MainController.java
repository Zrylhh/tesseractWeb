package cn.zrylhh.tesseractWeb.controller;

import cn.zrylhh.tesseractWeb.model.ResponseResult;
import cn.zrylhh.tesseractWeb.model.UpdateTextReq;
import cn.zrylhh.tesseractWeb.service.UploadImgService;
import cn.zrylhh.tesseractWeb.service.impl.UploadImgServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * 项目名：TesseractWeb.
 * 文件名：
 * 模块说明：
 * 修改历史：
 * 2021-05-13 - ZhaoLongTao - 创建.
 */
@RestController
@RequestMapping("/api/")
public class MainController {
    private Logger logger = LoggerFactory.getLogger(MainController.class);
    @Autowired
    UploadImgService uploadImgService;


    @Value("${static.upload}")
    private String uploadDir;

    //处理文件上传
    @RequestMapping(value="/uploadimg", method = RequestMethod.POST)
    public @ResponseBody ResponseResult uploadImg(@RequestParam("file") MultipartFile file,
                     HttpServletRequest request) {
//        String realPath = request.getSession().getServletContext().getRealPath("/");
        logger.info("realPath : "+uploadDir);
        ResponseResult responseResult = uploadImgService.procUploadImg(file,uploadDir);

        return responseResult;
    }

    //处理
    @RequestMapping(value="/updateText", method = RequestMethod.POST)
    public @ResponseBody ResponseResult updateText(@RequestBody UpdateTextReq reqDto,
                                                   HttpServletRequest request) {

        if(null != reqDto.getImgMd5() && reqDto.getImgMd5().length() > 0 && null != reqDto.getUpdateText() && reqDto.getUpdateText().length() > 0 ){
            ResponseResult responseResult = uploadImgService.updateText(reqDto);
            return responseResult;
        }

        return null ;
    }

    //处理
    @RequestMapping(value="/search", method = RequestMethod.POST)
    public @ResponseBody ResponseResult searchByText(@RequestParam String ocrText,
                                                   HttpServletRequest request) {

        if(null != ocrText && ocrText.length() > 0 ){
            ResponseResult responseResult = uploadImgService.searchByText(ocrText);
            return responseResult;
        }

        return null ;
    }

    //测试方法
    @RequestMapping(value="/test", method = RequestMethod.GET)
    public @ResponseBody ResponseResult test(HttpServletRequest request) {
        // 如何在springContext中获取bean
        // 通过ApplicationContext.getBean 即可获取bean对象
        ServletContext servletContext = request.getServletContext();

        // 1. 通过Spring的工具来获取ApplicationContext，
//        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        MathCalculator bean = webApplicationContext.getBean(MathCalculator.class);

        // 2. 通过一个实现ApplicationContextAware的工具类来获取bean对象
//        MathCalculator bean = SpringUtil.applicationContext.getBean(MathCalculator.class);

        // 错误方法，HttpServletRequest request 中获取的 servletContext 是服务容器（即tomcat）的上下文对象，不是spring的，所以这里转换失败
//        ApplicationContext applicationContext= (ApplicationContext) servletContext;
//        MathCalculator bean = applicationContext.getBean(MathCalculator.class);

        // 直接new出来的对象，不是spring构建的，所以无法被切面观察到
//        MathCalculator bean = new MathCalculator();

//        int div = bean.div(5, 2);

        return null ;
    }
}
