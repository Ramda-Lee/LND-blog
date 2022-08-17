package com.cos.blog.controller.api;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.domain.Board;
import com.cos.blog.domain.Reply;
import com.cos.blog.domain.RoleType;
import com.cos.blog.domain.User;
import com.cos.blog.dto.ReplyRequestDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.writing(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable Long id) {
        boardService.deleteContent(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable Long id, @RequestBody Board board) {
        boardService.updateContent(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


    // 데이터를 받을 때, 원래 컨트롤러에서 dto를 만들어서 받는게 좋음
    // dto를 사용하지 않는 이유는, 큰 프로젝트에서는 데이터가 오고가는게 많으니 꼭 써야함
    // 댓글쓰기
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@PathVariable("boardId") Long boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principalDetail) {

        boardService.writeReply(principalDetail.getUser(), boardId, reply);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
