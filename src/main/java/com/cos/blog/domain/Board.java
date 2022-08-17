package com.cos.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    private String category;

    @Lob //대용량 데이터터
    private String content; //섬머노트 라이브러리 <html>태그가 섞여서 디자인됨

    private int count; //조회수

    @ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One
    @JoinColumn(name = "userid")
    private User user; //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //mappedBy 연관관계의 주인이 아니다.(난 FK가 아니예요) DB에 칼럼을 만들지 마세요
    @JsonIgnoreProperties({"board"}) //무한 참조를 방지
    @OrderBy("id desc")
    private List<Reply> replys;

    @CreationTimestamp
    private Timestamp createDate;

}
