package com.rabbit.springboottemplatethymeleaf.controller;

import com.rabbit.springboottemplatethymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author chentao
 * Date 2019/3/3
 * Description
 **/
@Controller
public class IndexController {

    @GetMapping(value = {"", "/"})
    public ModelAndView index(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        User user = (User)request.getSession().getAttribute("user");
        if(user == null || (StringUtils.isEmpty(user.getName()) && StringUtils.isEmpty(user.getPassword()))){
            mv.setViewName("redirect:/user/login");
        } else {
            mv.setViewName("page/index");
            mv.addObject(user);
        }
        return mv;
    }
}

