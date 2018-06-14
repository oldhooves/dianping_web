package com.sunda.dto;

import com.sunda.bean.Comment;

/**
 * Created by ${laotizi} on 2018-06-14.
 */
public class CommentDto extends Comment {
    /**
     * 隐藏中间4位的手机号
     */
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
