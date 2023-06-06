package com.ali.employeeservice.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class MessageController {

    // Create the property  in employee-service.properties
//    @Value("${spring.boot.message}")
//    private String message;
//
//    @GetMapping("/users/message")
//    public String message (){
//        return message;
//    }

}
