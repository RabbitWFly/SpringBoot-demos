package com.rabbitwfly.controller;

import com.rabbitwfly.config.RabbitConfig;
import com.rabbitwfly.entity.Book;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public BookController(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public void defaultMessage(){
        Book book = new Book();
        book.setId("1");
        book.setName("come on to study Spring Boot");
        this.rabbitTemplate.convertAndSend(RabbitConfig.DEFAULT_BOOK_QUEUE, book);
        this.rabbitTemplate.convertAndSend(RabbitConfig.MANUAL_BOOK_QUEUE, book);
    }
}

