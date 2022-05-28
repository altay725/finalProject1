package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DefaultController {
    @GetMapping("/")
    public String getWelcomePage(){
        return "welcome";
    }


    @GetMapping("/login")
    public String getLoginPage(){
        return "sign-in";
    }
}