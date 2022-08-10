package com.cos.blog.controller;

import com.cos.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;


    @GetMapping({"", "/"})
    public String index(Model model, @PageableDefault(size = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.contentList(pageable));
        return "index";
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getContent(id));
        return "board/detail";
    }

    //User 권한 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getContent(id));
        return "board/updateForm";
    }
}
