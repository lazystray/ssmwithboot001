package com.zyl.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = {"com.zyl"},includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})},useDefaultFilters = false)
@EnableWebMvc
public class AppServletConfig implements WebMvcConfigurer {

    /**
     * 配置html文件的view解析器
     * @return
     */
    @Bean
    public InternalResourceViewResolver htmlViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");  //这里我是在WEB-INF下有个jsp文件夹
        viewResolver.setSuffix(".html");
        viewResolver.setCache(false);
        viewResolver.setViewNames("html/*");
        viewResolver.setOrder(5);
        return viewResolver;
    }

    /**
     * 配置jsp文件的view解析器
     * @return
     */
    @Bean
    public InternalResourceViewResolver jspViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");  //这里我是在WEB-INF下有个jsp文件夹
        viewResolver.setSuffix(".jsp");
        viewResolver.setCache(false);
        viewResolver.setViewNames("jsp/*");
        viewResolver.setOrder(6);
        return viewResolver;
    }

    /**
     *配置首页请求
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //匹配"http:localhost:8080/"的请求。这个斜杠表示默认什么都不输入，也就是首页，
        //后面的setViewName相当于 return "html/home";
        registry.addViewController("/").setViewName("html/home");
    }

    /**
     * 配置html文件和jsp文件的资源映射路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/*.jsp").addResourceLocations("/WEB-INF/jsp/");
        registry.addResourceHandler("/*.html").addResourceLocations("/WEB-INF/html/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


}
