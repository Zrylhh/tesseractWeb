package cn.zrylhh.tesseractWeb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class StaticFileConfig extends WebMvcConfigurationSupport {

    @Value("${static.upload}")
    public String uploadPath;

    /**
     * 过滤访问资源文件
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:public/");
        registry.addResourceHandler("/up/**").addResourceLocations("file:" + uploadPath);
        super.addResourceHandlers(registry);
    }
}
