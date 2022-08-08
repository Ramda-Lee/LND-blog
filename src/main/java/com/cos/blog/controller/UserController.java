package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    //인증이 안된 사용자들이 출입할 수 있는 경로를 /suth/** 허용
    //그냥 주소가 "/" 이면 index.html 허용
    // static이하에 있는 /js/**, 등등
    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }


}
