## Swagger

---

1. Web 项目
2. 倒包mvn

```xml
    <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>

```

3. helloworld

4. 配置swagger =》config

```java
@Configuration
@EnableSwagger2 //开启swagger
public class SwaggerConfig {
}
```



5. 测试运行

```java
package com.zf.swagger.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
//@EnableOpenApi //开启swagger  3.0
@EnableSwagger2  //2.0
public class SwaggerConfig {

    // 配置了 Swagger的bean 实例
    @Bean
    public Docket dokect(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());

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
```

## Swagger 配置扫描借口

Docket.select()

```java
     return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // RequestHandlerSelectors 是配置要扫描接口的方式
//        basePackage("com.zf.swagger.controller")：指定要扫描的包
                // any() 扫描全部
                // none() 都不扫描
                //.withClassAnnotation(xxx.class) 扫描类上的注解，注解的反射对象
                //withMethodAnnotation(GetMapping.class) 扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.zf.swagger.controller"))
                //paths(PathSelectors) // 过滤什么路径  只扫描 zf下的路径
                .paths(PathSelectors.ant("/zf/**"))
                .build();

    }
```

是否配置成功

```java
@Bean
public Docket dokect(){

    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .enable(false)    // enable 是否启用swagger如果为false, 则swagger 不能在浏览器中使用

            .select()
            .apis(RequestHandlerSelectors.basePackage("com.zf.swagger.controller"))
            .build();

}
```

我只希望swagger在生活从那环境中使用，在发布的时候不使用？

1. 判断是不是生产环境 flag = false
2. 注入enable（flag）

```java
  @Bean
    public Docket dokect(Environment environment){


//        获取项目环境
        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev","test");
        //通过environment.acceptsProfiles判断是否处在自己设定的环境中
        boolean flag = environment.acceptsProfiles(profiles);


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)    // enable 是否启用swagger如果为false, 则swagger 不能在浏览器中使用

                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zf.swagger.controller"))
                .build();

    }
```

## 配置API文档的分组

.        .groupName("王治方")

```java
return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .groupName("王治方")
        .enable(flag)    // enable 是否启用swagger如果为false, 则swagger 不能在浏览器中使用

        .select()
        .apis(RequestHandlerSelectors.basePackage("com.zf.swagger.controller"))
        .build();
```

如何配置多个分组？ 写多个Docket实例即可

```java
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
```

实体类配置-注释

```java
 @ApiOperation("Hello 控制类")
@GetMapping("/hello2")
public String hello2(@ApiParam("用户名") String username){
  return "hello" + username;
}
```

```java
@ApiModel("用户实体类")
public class User {
    @ApiModelProperty("用户名")
    public String username;
    @ApiModelProperty("密码")
    public String password;



    
}
```

```java
package com.zf.swagger.controller;


import com.zf.swagger.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

       // /error
     @GetMapping(value = "/hello")
     public String hello(){
       return "Hello world";
     }


     // 只要接口中，返回值存在实体类就会被扫描
    @PostMapping(value = "/user")
    public User user(){
        return new User();
    }

    @ApiOperation("Hello 控制类")
   @GetMapping("/hello2")
   public String hello2(@ApiParam("用户名") String username){
     return "hello" + username;
   }


    @ApiOperation("Post 测试")
    @PostMapping("/postt")
    public User hello2(@ApiParam("用户") User user){
        return user;
    }



}
```

我们可以通过Swagger给一些难以理解的属性和接口增加注释

接口文档实时更新

可以在线测试

