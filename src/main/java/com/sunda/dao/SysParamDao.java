package com.sunda.dao;

import com.sunda.bean.SysParam;

/**
 * Created by ${laotizi} on 2018-06-06.
 */
public interface SysParamDao {

    /**
     * 根据KEY获取对应的系统参数值
     *
     * @param key
     * @return 系统参数值
     */
    SysParam selectByKey(String key);

    /**
     * 根据KEY修改对应的系统参数值
     *
     * @param sysParam
     * @return 影响行数
     */
    int updateByKey(SysParam sysParam);
}
