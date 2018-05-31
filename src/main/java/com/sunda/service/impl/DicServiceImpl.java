package com.sunda.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.sunda.bean.Dic;
import com.sunda.dao.DicDao;
import com.sunda.service.DicService;
import org.springframework.stereotype.Service;

@Service
public class DicServiceImpl implements DicService {
    
    @Resource
    private DicDao dicDao;
    
    @Override
    public List<Dic> getListByType(String type) {
	Dic dic = new Dic();
	dic.setType(type);
	return dicDao.select(dic);
    }
}
