package com.rabbitwfly;

import com.battcn.swagger.annotation.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwagger2Doc
@SpringBootApplication
public class Chapter9Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter9Application.class, args);
    }

}
