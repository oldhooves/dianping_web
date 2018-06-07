package com.sunda.controller.system;

import com.sunda.bean.Orders;
import com.sunda.dto.AdDto;
import com.sunda.dto.OrdersDto;
import com.sunda.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ${laotizi} on 2018-06-06.
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;


    /**
     * 初始化
     */
    @RequestMapping
    public String init(Model model) {
        OrdersDto ordersDto = new OrdersDto();
        model.addAttribute("list", ordersService.searchByPage(ordersDto));
        model.addAttribute("searchParam", ordersDto);
        return "/content/orderList";
    }

    /**
     * 查询
     */
    @RequestMapping("/search")
    public String search(Model model, OrdersDto ordersDto) {
        model.addAttribute("list", ordersService.searchByPage(ordersDto));
        model.addAttribute("searchParam", ordersDto);
        return "/content/orderList";
    }

}
