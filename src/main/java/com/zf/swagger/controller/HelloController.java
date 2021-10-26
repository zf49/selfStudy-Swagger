package com.zf.swagger.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

       // /error
     @RequestMapping(value = "/hello")
     public String hello(){
       return "Hello world";
     }


}
