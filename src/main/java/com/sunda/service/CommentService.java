package com.sunda.service;

import com.sunda.bean.Page;
import com.sunda.dto.CommentDto;
import com.sunda.dto.CommentForSubmitDto;
import com.sunda.dto.CommentListDto;

import java.util.List;

/**
 * Created by ${laotizi} on 2018-06-14.
 */
public interface CommentService {

    /**
     * 保存评论
     * @param commentForSubmitDto 提交的评论DTO对象
     * @return 是否保存成功：true-成功，false-失败
     */
    boolean add(CommentForSubmitDto commentForSubmitDto);

    /**
     * 按分页条件，根据商户ID获取商户下的评论列表dto
     * @param businessId 商户ID
     * @param page 分页对象
     * @return 评论列表dto
     */
    CommentListDto getListByBusinessId(Long businessId, Page page);

    /**
     * 根据查询条件获取评论列表
     * @param dto
     * @return
     */
    List<CommentDto> getListByConditioin(CommentDto dto);
}
