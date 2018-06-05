package com.sunda.service.impl;

import com.sunda.bean.Member;
import com.sunda.cache.CodeCache;
import com.sunda.cache.TokenCache;
import com.sunda.dao.MemberDao;
import com.sunda.service.MemberService;
import com.sunda.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ${laotizi} on 2018-06-05.
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    private final static Logger logger = LoggerFactory
            .getLogger(MemberService.class);

    @Override
    public boolean exists(Long phone) {
        Member member = new Member();
        member.setPhone(phone);
        List<Member> list = memberDao.select(member);
        return list != null && list.size() == 1;
    }

    @Override
    public boolean saveCode(Long phone, String code) {
        // TODO 在真实环境中，改成借助第三方实现
        CodeCache codeCache = CodeCache.getInstance();
        return codeCache.save(phone, MD5Util.getMD5(code));
    }

    @Override
    public boolean sendCode(Long phone, String content) {
        logger.info(phone + "|" + content);
        return true;
    }

    @Override
    public String getCode(Long phone) {
        // TODO 在真实环境中，改成借助第三方实现
        CodeCache codeCache = CodeCache.getInstance();
        return codeCache.getCode(phone);
    }

    @Override
    public void saveToken(String token, Long phone) {
        TokenCache tokenCache = TokenCache.getInstance();
        tokenCache.save(token, phone);
    }

    @Override
    public Long getPhone(String token) {
        TokenCache tokenCache = TokenCache.getInstance();
        return tokenCache.getPhone(token);
    }

    @Override
    public Long getIdByPhone(Long phone) {
        Member member = new Member();
        member.setPhone(phone);
        List<Member> list = memberDao.select(member);
        if (list != null && list.size() == 1) {
            return list.get(0).getId();
        }
        return null;
    }
}
