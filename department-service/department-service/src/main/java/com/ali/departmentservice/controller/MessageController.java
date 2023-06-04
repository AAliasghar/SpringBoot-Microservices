package com.ali.departmentservice.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class MessageController {

    // Create the property  in department-service.properties
//    @Value("${spring.boot.message}")
//    private String message;
//
//    @GetMapping("message")
//    public String message (){
//        return message;
//    }

}
