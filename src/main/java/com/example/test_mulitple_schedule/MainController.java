package com.example.test_mulitple_schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/mq")
@RestController
public class MainController {

    private final Sender sender;

    @Autowired
    public MainController(Sender sender) {
        this.sender = sender;
    }

    @RequestMapping(value = "/send")
    public ResponseEntity<String> send(){
        sender.send();
        return ResponseEntity.ok("OK");
    }
}

