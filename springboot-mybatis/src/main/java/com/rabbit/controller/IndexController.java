package com.rabbit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author chentao
 * Date 2019/2/21
 * Description
 **/
@Controller
public class IndexController extends AbstractController{
    @RequestMapping("/main")
    public String main(Model model){
        model.addAttribute("ctx", getContextPath()+"/");
        return "main";
    }

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("ctx", getContextPath()+"/");
        return "index";
    }
}

