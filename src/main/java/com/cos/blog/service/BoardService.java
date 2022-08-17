package com.cos.blog.service;

import com.cos.blog.domain.Board;
import com.cos.blog.domain.Reply;
import com.cos.blog.domain.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public void writing(Board board, User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> contentList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board getContent(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("상세조회 실패 : 아이디를 찾을 수 없습니다.");
        });
    }

    @Transactional
    public void deleteContent(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updateContent(Long id, Board requestBoard) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("글 수정 실패 : 아이디를 찾을 수 없습니다.");
        }); //영속화 완료
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        board.setCategory(requestBoard.getCategory());
        // 해당 함수 종료시(Service가 종료될 때) 트랜젝션이 종료될떄 더티체킹(자동 업데이트)가 됨.
    }

    @Transactional
    public void writeReply(User user, Long boardId, Reply requestReply) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 ID를 찾을 수 없습니다.");
                });
        requestReply.setUser(user);
        requestReply.setBoard(board);

        replyRepository.save(requestReply);
    }
}
