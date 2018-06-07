package com.sunda.service.impl;

import com.sunda.bean.Orders;
import com.sunda.constant.CommentStateConst;
import com.sunda.dao.OrdersDao;
import com.sunda.dto.OrderForBuyDto;
import com.sunda.dto.OrdersDto;
import com.sunda.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ${laotizi} on 2018-06-06.
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Value("${businessImage.url}")
    private String businessImageUrl;

    @Override
    public boolean add(OrdersDto ordersDto) {
        Orders orders = new Orders();
        BeanUtils.copyProperties(ordersDto, orders);
        orders.setCommentState(CommentStateConst.NOT_COMMENT);
        orders.setCreateTime(new Date());
        ordersDao.insert(orders);
        return true;
    }

    @Override
    public OrdersDto getById(Long id) {
        OrdersDto result = new OrdersDto();
        Orders orders = ordersDao.selectById(id);
        BeanUtils.copyProperties(orders, result);
        return result;
    }

    @Override
    public List<OrdersDto> getListByMemberId(Long memberId) {
        List<OrdersDto> result = new ArrayList<OrdersDto>();
        Orders ordersForSelect = new Orders();
        ordersForSelect.setMemberId(memberId);
        List<Orders>  ordersList = ordersDao.select(ordersForSelect);
        for(Orders orders : ordersList) {
            OrdersDto ordersDto = new OrdersDto();
            result.add(ordersDto);
            BeanUtils.copyProperties(orders, ordersDto);
            ordersDto.setImg(businessImageUrl + orders.getBusiness().getImgFileName());
            ordersDto.setTitle(orders.getBusiness().getTitle());
            ordersDto.setCount(orders.getBusiness().getNumber());
            //TODO 测试先后顺序的影响
        }
        return result;
    }

    @Override
    public List<OrdersDto> searchByPage(OrdersDto ordersDto) {
        List<OrdersDto> result = new ArrayList<OrdersDto>();
        Orders condition = new Orders();
        BeanUtils.copyProperties(ordersDto, condition);
        List<Orders> adList = ordersDao.selectAll(condition);
        for (Orders orders : adList) {
            OrdersDto ordersDtoTemp = new OrdersDto();
            result.add(ordersDtoTemp);
            BeanUtils.copyProperties(orders, ordersDtoTemp);
        }
        return result;
    }

    @Override
    public boolean save(OrderForBuyDto orderForBuyDto, Long memberId) {
        Orders orders = new Orders();
        orders.setNum(orderForBuyDto.getNum());
        orders.setPrice(orderForBuyDto.getPrice());
        orders.setBusinessId(orderForBuyDto.getId());
        orders.setMemberId(memberId);
        orders.setCommentState(CommentStateConst.NOT_COMMENT);
        orders.setCreateTime(new Date());
        return ordersDao.insert(orders);
    }


}
