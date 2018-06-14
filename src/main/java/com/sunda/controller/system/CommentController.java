package com.sunda.controller.system;

import com.sunda.dto.CommentDto;
import com.sunda.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ${laotizi} on 2018-06-14.
 */
@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @RequestMapping
    public String init(Model model) {
        CommentDto commentDto = new CommentDto();
        model.addAttribute("list",commentService.getListByConditioin(commentDto));
        model.addAttribute("searchParam",commentDto);
        return "/content/commentList";
    }

    @RequestMapping("/search")
    public String search(CommentDto dto, Model model){
        model.addAttribute("commentList",commentService.getListByConditioin(dto));
        model.addAttribute("searchParam", dto);
        return "/content/commentList";
    }


}
