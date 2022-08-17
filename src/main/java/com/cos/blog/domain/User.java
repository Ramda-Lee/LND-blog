package com.cos.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

//ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity //User 클래스가 MySQL에 자동으로 테이블 생성 된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다
    private Long id; //auto_increment

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 100) //hash(비밀번호 암호화)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    //DB는 RoleType이 없다
    @Enumerated(EnumType.STRING)
    private RoleType role; //Enum을 쓰는게 졸다.

    private String oauth;

    @CreationTimestamp //시간이 자동 입력
    private LocalDate createDate;

    @PrePersist
    public void createDate(){
        this.createDate = LocalDate.now();
    }

}
