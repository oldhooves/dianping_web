package com.sunda.task;

import com.sunda.bean.SysParam;
import com.sunda.constant.SysParamKeyConst;
import com.sunda.dao.BusinessDao;
import com.sunda.dao.SysParamDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${laotizi} on 2018-06-06.
 */
@Component("BusinessTask")
public class BusinessTask {

    private static final Logger logger = LoggerFactory.getLogger(BusinessTask.class);

    @Autowired
    private BusinessDao businessDao;

    @Autowired
    private SysParamDao sysParamDao;


    /**
     * 同步已售数量
     */
    @Transactional
    public void synNumber() {
        logger.info("synNumber start!");
        //先获取上次同步的时间(最后同步时间)
        SysParam sysParam = sysParamDao.selectByKey(SysParamKeyConst.LAST_SYNC_NUMBER_TIME);
        Map<String, Date> map = new HashMap<>();
        map.put("startTime", sysParam.getParamValue());
        System.out.println(sysParam.getParamValue());
        //以当前系统事件作为同步的截至时间，同时也做为下次同步的起始时间
        Date endTime = new Date();
        //按这样起始时间-结束时间同步：商户对应的[已售数量]
        //如果起始时间为null，那说明是第一次同步，需要将历史数据全部同步，一直同步到当前系统时间为止。
        businessDao.updateNumber(map);
        //将当前这个系统时间更新到系统参数表中，做为下次同步的起始时间
        SysParam sysParamForUpdate = new SysParam();
        sysParamForUpdate.setParamKey(SysParamKeyConst.LAST_SYNC_NUMBER_TIME);
        sysParamForUpdate.setParamValue(endTime);
        sysParamDao.updateByKey(sysParamForUpdate);
        logger.info("sysNumber end!");
    }

    /**
     * 同步星级
     */
    @Transactional
    public void synStar() {
        logger.info("synStar start!");
        //先获取上次同步的时间(最后同步时间)
        SysParam sysParam = sysParamDao.selectByKey(SysParamKeyConst.LAST_SYNC_STAR_TIME);
        Map<String, Date> map = new HashMap<>();
        map.put("startTime", sysParam.getParamValue());
        //以当前时间做为同步的截至时间，同时也做为下次同步的起始时间
        Date endTime = new Date();
        map.put("endTime", endTime);
        //按这样起始时间-结束时间同步：商户对应的[星星总数]、[评论总次数]
        //如果起始时间为null，那说明是第一次同步，需要将历史数据全部同步，一直同步到当前系统时间为止。
        businessDao.updateStar(map);
        //将当前这个系统时间更新到系统参数表中，作为下次同步的起始时间。
        SysParam sysParamForUpdate = new SysParam();
        sysParamForUpdate.setParamKey(SysParamKeyConst.LAST_SYNC_STAR_TIME);
        sysParamForUpdate.setParamValue(endTime);
        sysParamDao.updateByKey(sysParamForUpdate);
        logger.info("sysStar end!");
    }
}
