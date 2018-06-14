package com.sunda.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by ${laotizi} on 2018-06-14.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentListDto {

    private boolean hasMore;
    private List<CommentDto> data;

    public boolean isHasMore() {
        return hasMore;
    }
    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
    public List<CommentDto> getData() {
        return data;
    }
    public void setData(List<CommentDto> data) {
        this.data = data;
    }
}
