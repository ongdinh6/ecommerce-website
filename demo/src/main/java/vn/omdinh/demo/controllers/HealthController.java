package vn.omdinh.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/status")
    String status() {
        return "Hello world!";
    }
}
