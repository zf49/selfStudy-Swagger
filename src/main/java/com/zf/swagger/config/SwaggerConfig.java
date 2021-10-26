package com.zf.swagger.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Generated;
import java.util.ArrayList;

@Configuration
//@EnableOpenApi //开启swagger  3.0
@EnableSwagger2  //2.0
public class SwaggerConfig {

    // 配置了 Swagger的bean 实例

    @Bean
    public Docket dokect(Environment environment){


//        获取项目环境
        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev","test");
        //通过environment.acceptsProfiles判断是否处在自己设定的环境中
        boolean flag = environment.acceptsProfiles(profiles);


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("王治方")
                .enable(flag)    // enable 是否启用swagger如果为false, 则swagger 不能在浏览器中使用

                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zf.swagger.controller"))
                .build();

    }

    @Bean
    public Docket dokect1(Environment environment){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("A");
    }

    @Bean
    public Docket dokect2(Environment environment){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("B");
    }

    @Bean
    public Docket dokect3(Environment environment){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("C");
    }


    // 配置swagger信息= apiInfo()

    private ApiInfo apiInfo(){
        Contact contact = new Contact("Chris","https://github.com/zf49","zwan686@aucklanduni.ac.nz");

       return new ApiInfo(
                "Swagger API document of Chris",
                "十年寒窗无人问，一朝成名天下知",
                "v1.0",
                "https://github.com/zf49",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>()
        );
    }
    


}
