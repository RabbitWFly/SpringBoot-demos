package com.rabbitwfly.controller;

import com.rabbitwfly.pojo.Book;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Validated
@RestController
public class ValidateController {

    @GetMapping("/test2")
    public String test2(@NotBlank(message = "name 不能为空")@Length(min = 2, max = 10, message = "name 长度必须在 {min} - {max} 之间") String name){
        return "success";
    }

    @GetMapping("/test3")
    public String test3(@Validated Book book){
        return "success";
    }
}

