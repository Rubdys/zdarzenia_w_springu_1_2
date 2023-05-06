package com.example.zdarzenia_w_springu_1_2.controller;

import com.example.zdarzenia_w_springu_1_2.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/agent")
@Slf4j
public class JavaAgentController {

    @PostMapping(path = "createUser")
    public void createUser(){
        log.info("Invoking create user method from JavaAgentController");
        User user = new User();
        user.doSomething();
    }
}
