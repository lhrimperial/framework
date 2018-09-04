package com.github.framework.archetype.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 *
 */
@Controller
public class FreemarkerController {

    @RequestMapping("/demo")
    public String demo(Map<String, Object> map) {
        map.put("descrip", "It's a springboot integrate freemarker's demo!!!!");
        return "demo";
    }

}