package vn.omdinh.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TemplateController {

    @GetMapping(path = { "/", "/products" })
    ModelAndView index() {
        return new ModelAndView("index");
    }
}
