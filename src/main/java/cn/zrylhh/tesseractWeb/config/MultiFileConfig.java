package cn.zrylhh.tesseractWeb.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class MultiFileConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement(){

        MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
        multipartConfigFactory.setMaxFileSize(DataSize.ofMegabytes(5));
        multipartConfigFactory.setMaxRequestSize(DataSize.ofMegabytes(5));
        return multipartConfigFactory.createMultipartConfig();
    }
}
