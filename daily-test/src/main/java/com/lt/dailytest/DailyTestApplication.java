package com.lt.dailytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class DailyTestApplication {

    public static void main(String[] args) {

        SpringApplication.run(DailyTestApplication.class, args);
    }

    @RequestMapping(value = {"", "/", "\\"})
    public ModelAndView getIndexPage(String req) {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
