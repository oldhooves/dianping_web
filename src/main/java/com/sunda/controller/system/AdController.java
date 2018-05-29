package com.sunda.controller.system;

import com.sunda.constant.PageCodeEnum;
import com.sunda.dto.AdDto;
import com.sunda.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ${laotizi} on 2018-05-29.
 */
@Controller
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdService adService;
    @RequestMapping
    public String init(){
        return "/content/adList";
    }

    @RequestMapping("/addInit")
    public String addInit(){
        return "/content/adAdd";
    }

    /**
     * 查询
     */
    @RequestMapping("/search")
    public String search(Model model, AdDto adDto) {
        model.addAttribute("list", adService.searchByPage(adDto));
        model.addAttribute("searchParam", adDto);
        return "/content/adList";
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    public String add(AdDto adDto, Model model) {
        if (adService.add(adDto)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_FAIL);
        }
        return "/content/adAdd";
    }

}
