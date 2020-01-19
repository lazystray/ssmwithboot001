package com.zyl.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.io.IOException;


@Configuration
@PropertySource("classpath:jdbc.properties")
@ComponentScan(basePackages = {"com.zyl"},excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})})
public class AppRootConfig {


    public AppRootConfig() {
        System.out.println("AppRootConfig");
    }

    @Bean
    public DruidDataSource getDataSource(
            @Value("${jdbc.driver}") String driver,
            @Value("${jdbc.url}") String url,
            @Value("${jdbc.user}") String username,
            @Value("${jdbc.password}") String password) {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean getFactory(@Autowired DataSource ds) throws IOException {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(ds);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperLocations = resolver.getResources("classpath:mapper/*.xml");
        factory.setMapperLocations(mapperLocations);
        return factory;
    }

    @Bean("sqlSessionTemplate")
    public SqlSessionTemplate getSqlSessionTemplate(@Autowired DataSource ds) throws IOException {
        SqlSessionFactoryBean sqlSessionFactory = getFactory(ds);
        SqlSessionTemplate sqlSessionTemplate = null;
        try {
            sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory.getObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSessionTemplate;
    }

    @Bean
    public MapperScannerConfigurer getScanner() {
        MapperScannerConfigurer scanner = new MapperScannerConfigurer();
        scanner.setBasePackage("com.zyl.dao");
        //sqlSessionFactory和sqlSessionTemplate只要二选一就可以了
        /*scanner.setSqlSessionFactoryBeanName("sqlSessionFactory");*/
        scanner.setSqlSessionTemplateBeanName("sqlSessionTemplate");
        return scanner;
    }
}