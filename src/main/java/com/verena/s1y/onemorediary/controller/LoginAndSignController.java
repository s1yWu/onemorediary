package com.verena.s1y.onemorediary.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/one-more-dairy")
public class LoginAndSignController {

    @RequestMapping("/login")
    public String Login(){
        return "login in";
    }

}
