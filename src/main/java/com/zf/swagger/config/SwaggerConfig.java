package com.zf.swagger.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@EnableOpenApi //开启swagger  3.0
@EnableSwagger2  //2.0
public class SwaggerConfig {

    // 配置了 Swagger的bean 实例
    @Bean
    public Docket dokect(){

        return new Docket(DocumentationType.SWAGGER_2);

    }



}
