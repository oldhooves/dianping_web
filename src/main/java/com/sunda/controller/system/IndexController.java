package com.sunda.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ${laotizi} on 2018-05-28.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping
    public String init(){
        return "/system/index";
    }
}
