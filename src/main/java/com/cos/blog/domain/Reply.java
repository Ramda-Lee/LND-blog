package com.cos.blog.domain;

import com.cos.blog.dto.ReplyRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne //Many = reply, one = board
    @JoinColumn(name = "boardid")
    private Board board;

    @ManyToOne //Many = reply, one = user
    @JoinColumn(name = "userid")
    private User user;

    @CreationTimestamp
    private Timestamp createDate;

    public void update(User user, Board board, String content) {

        setUser(user);
        setBoard(board);
        setContent(content);
    }
}
