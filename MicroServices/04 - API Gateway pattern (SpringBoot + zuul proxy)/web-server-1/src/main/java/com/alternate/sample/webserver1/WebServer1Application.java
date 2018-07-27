package com.alternate.sample.webserver1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class WebServer1Application {

    @GetMapping("greet")
    public ResponseEntity<Map> greeting() {
        Map<String, String> greet = new HashMap<>();
        greet.put("message", "greeting from web server 1");

        return ResponseEntity.ok(greet);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebServer1Application.class, args);
    }
}
