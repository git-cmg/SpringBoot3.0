package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 单页应用控制器
 */
@Controller
public class SPAController {
    @RequestMapping(value = "/{path:[^.]*}")
    public String redirect() {
        return "forward:/index.html";
    }
}
