package com.alternate.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    /*
        The @ResponseBody annotation [...] can be put on a method and indicates that the return type should be
        written straight to the HTTP response body (and not placed in a Model, or interpreted as a view name).
     */
    @GetMapping("/greet")
    public String greet(@RequestParam("name") String name) {
        return String.format("Hello %s", name);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class);
    }
}
