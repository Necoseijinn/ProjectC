package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {

    @RequestMapping("hello")
    public String test(Product product){
        System.out.println(product);
        return "hello";
    }
}
