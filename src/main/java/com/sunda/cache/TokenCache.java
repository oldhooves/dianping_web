package com.sunda.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${laotizi} on 2018-06-05.
 */
public class TokenCache {

    private static TokenCache instance;

    private Map<String, Long> tokenMap;

    private TokenCache() {
        tokenMap = new HashMap<>();
    }

    public static TokenCache getInstance() {
        if (instance == null) {
            synchronized (TokenCache.class) {
                if (instance == null) {
                    instance = new TokenCache();
                }
            }
        }
        return instance;
    }

    /**
     * 保存token与对应的手机号
     * @param token
     * @param phone 手机号
     */
    public void save(String token, Long phone) {
        tokenMap.put(token, phone);
    }

    /**
     * 根据token获取用户信息(手机号)
     * @param token
     * @return 手机号
     */
    public Long getPhone(String token) {
        return tokenMap.get(token);
    }
}