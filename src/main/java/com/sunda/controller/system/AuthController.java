package com.sunda.controller.system;

import com.sunda.constant.DicTypeConst;
import com.sunda.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ${laotizi} on 2018-06-19.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private DicService dicService;

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("httpMethodList", dicService.getListByType(DicTypeConst.HTTP_METHOD));
        return "/system/auth";
    }
}
