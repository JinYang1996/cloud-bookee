package com.bookee.bookee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.util.Properties;

@Configuration
public class FreeMarkerConfig {

    @Bean(name="freemarkerConfiguration")
    public FreeMarkerConfigurationFactoryBean freemarkerConfiguration(){
        FreeMarkerConfigurationFactoryBean freemarkerConfiguration = new FreeMarkerConfigurationFactoryBean();
        freemarkerConfiguration.setTemplateLoaderPath("classpath:/freemarker/");
        freemarkerConfiguration.setDefaultEncoding("utf-8");
//hhh
        Properties properties = new Properties();
        properties.put("template_update_delay", "30");
        properties.put("default_encoding", "UTF-8");
        properties.put("locale", "zh_CN");
        properties.put("classic_compatible", "true");
        properties.put("datetime_format", "yyyy-MM-dd HH:mm:ss");
        freemarkerConfiguration.setFreemarkerSettings(properties);
        return freemarkerConfiguration;
    }
}