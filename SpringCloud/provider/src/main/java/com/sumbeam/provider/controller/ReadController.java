package com.sumbeam.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider/")
public class ReadController {

    @GetMapping("getData")
    public Object getData(){

        return "null";
    }
}
