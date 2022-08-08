package com.cos.blog.repository;

import com.cos.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//자동으로 bean 등록이된다 -> @Repository가 생략가능
public interface UserRepository extends JpaRepository<User, Long> {

    //SELECT * FROM user WHERE username = 1?;
    Optional<User> findByUsername(String username);
}
