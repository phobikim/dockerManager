package com.docker.dockermanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/#")
public class AuthController {

    @GetMapping("/auth")
    public String login(){
        System.out.println("login 체크 함수");
        return "login";
    }

    @PostMapping("/auth")
    public String checkAuth(@RequestParam("id") String id , @RequestParam("pw") String pw) {
        System.out.println("id , pw = " + id + pw);
        return "redirect:/docker/home";
    }
}
