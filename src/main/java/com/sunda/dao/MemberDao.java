package com.sunda.dao;

import com.sunda.bean.Member;

import java.util.List;

/**
 * Created by ${laotizi} on 2018-06-05.
 */
public interface MemberDao {

    /**
     * 根据查询条件查询会员列表
     * @param member 查询条件
     * @return 会员列表
     */
    List<Member> select(Member member);
}
