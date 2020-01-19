package com.zyl.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 注意 注意，有个特别重要的点需要明确，springBoot的启动类必须要package的上层(不一定是根目录)
 * 所需要扫描的component必须在启动类的子包下面，不然扫描不到交给spring托管的配置类，项目不能正常启动
 * 如果不在同一包目录下，可以用@ComponentScan注解指定需要扫描路径
 * @SpringBootApplication = (默认属性)@Configuration + @EnableAutoConfiguration + @ComponentScan。
 * 自己加的@ComponentScan是覆盖了默认的值(默认扫描启动类文件所在包的子包)
 * 本工程用自定义注解配置代替了springBoot帮我们做好的配置，省略了application.properties文件
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.zyl")
public class Test001Application {

	public static void main(String[] args) {
		SpringApplication.run(Test001Application.class, args);
	}

}
