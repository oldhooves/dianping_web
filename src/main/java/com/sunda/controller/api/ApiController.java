package com.sunda.controller.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunda.bean.Ad;
import com.sunda.dto.AdDto;
import com.sunda.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by ${laotizi} on 2018-05-28.
 */
@RestController
@RequestMapping("/api")
public class ApiController {


    @Autowired
    private AdService adService;


    @Value("${ad.number}")
    private int adNumber;

    /**
     * 首页 —— 广告（超值特惠）
     */
    @RequestMapping(value = "/homead", method = RequestMethod.GET)
    public List<AdDto> homead() {
        AdDto adDto = new AdDto();
        adDto.getPage().setPageNumber(adNumber);
        return adService.searchByPage(adDto);
    }
}
