package com.example.apispringangular.contoller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@CrossOrigin(origins = "*")
public class HelloController {


    @GetMapping("/hello")
    public String helloWorld() {
        return "Olá mundo api";
    }
}
