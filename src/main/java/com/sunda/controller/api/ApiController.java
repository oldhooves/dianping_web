package com.sunda.controller.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunda.bean.Ad;
import com.sunda.constant.ApiCodeEnum;
import com.sunda.dto.AdDto;
import com.sunda.dto.ApiCodeDto;
import com.sunda.dto.BusinessDto;
import com.sunda.dto.BusinessListDto;
import com.sunda.service.AdService;
import com.sunda.service.BusinessService;
import com.sunda.service.MemberService;
import com.sunda.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Autowired
    private BusinessService businessService;

    @Autowired
    private MemberService memberService;

    @Value("${ad.number}")
    private int adNumber;

    @Value("${businessSearch.number}")
    private int businessSearchNumber;

    @Value("${businessHome.number}")
    private int businessHomeNumber;

    /**
     * 首页 —— 广告（超值特惠）
     */
    @RequestMapping(value = "/homead", method = RequestMethod.GET)
    public List<AdDto> homead() {
        AdDto adDto = new AdDto();
        adDto.getPage().setPageNumber(adNumber);
        return adService.searchByPage(adDto);
    }

    /**
     * 首页 —— 推荐列表（猜你喜欢）
     */
    @RequestMapping(value = "/homelist/{city}/{page.currentPage}", method = RequestMethod.GET)
    public BusinessListDto homelist(BusinessDto businessDto) {
        businessDto.getPage().setPageNumber(businessHomeNumber);
        return businessService.searchByPageForApi(businessDto);
    }

    /**
     * 搜索结果页 - 三个参数
     */
    @RequestMapping(value = "/search/{page.currentPage}/{city}/{category}/{keyword}", method = RequestMethod.GET)
    public BusinessListDto searchByKeyword(BusinessDto businessDto) {
        businessDto.getPage().setPageNumber(businessSearchNumber);
        return businessService.searchByPageForApi(businessDto);
    }

    /**
     * 搜索结果页 - 两个参数
     */
    @RequestMapping(value = "/search/{page.currentPage}/{city}/{category}", method = RequestMethod.GET)
    public BusinessListDto search(BusinessDto businessDto) {
        businessDto.getPage().setPageNumber(businessSearchNumber);
        return businessService.searchByPageForApi(businessDto);
    }

    /**
     * 详情页 - 商户信息
     */
    @RequestMapping(value = "/detail/info/{id}", method = RequestMethod.GET)
    public BusinessDto detail(@PathVariable("id") long id) {
        return businessService.getById(id);
    }

    /**
     * 根据手机号下发短信验证码
     */
    @RequestMapping(value = "/sms", method = RequestMethod.POST)
    public ApiCodeDto sms(@RequestParam("username") Long username) {
        ApiCodeDto dto;
        // 1、验证用户手机号是否存在（是否注册过）
        if (memberService.exists(username)) {
            // 2、生成6位随机数
            String code = String.valueOf(CommonUtil.random(6));
            // 3、保存手机号与对应的md5(6位随机数)（一般保存1分钟，1分钟后失效）
            if (memberService.saveCode(username, code)) {
                // 4、调用短信通道，将明文6位随机数发送到对应的手机上。
                if (memberService.sendCode(username, code)) {
                    dto = new ApiCodeDto(ApiCodeEnum.SUCCESS.getErrno(), code);
                } else {
                    dto = new ApiCodeDto(ApiCodeEnum.SEND_FAIL);
                }
            } else {
                dto = new ApiCodeDto(ApiCodeEnum.REPEAT_REQUEST);
            }
        } else {
            dto = new ApiCodeDto(ApiCodeEnum.USER_NOT_EXISTS);
        }
        return dto;
    }

    /**
     * 会员登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiCodeDto login(@RequestParam("username") Long username, @RequestParam("code") String code) {
        ApiCodeDto apiCodeDto;
        // 1、用手机号取出保存的md5(6位随机数)，能取到，并且与提交上来的code值相同为校验通过
        String saveCode = memberService.getCode(username);
        if (saveCode != null) {
            if (saveCode.equals(code)) {
                // 2、如果校验通过，生成一个32位的token
                String token = CommonUtil.getUUID();
                // 3、保存手机号与对应的token（一般这个手机号中途没有与服务端交互的情况下，保持10分钟）
                memberService.saveToken(token, username);
                // 4、将这个token返回给前端
                apiCodeDto = new ApiCodeDto(ApiCodeEnum.SUCCESS);
                apiCodeDto.setToken(token);
            } else {
                apiCodeDto = new ApiCodeDto(ApiCodeEnum.CODE_ERROR);
            }
        } else {
            apiCodeDto = new ApiCodeDto(ApiCodeEnum.CODE_INVALID);
        }
        return apiCodeDto;
    }



}
