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
