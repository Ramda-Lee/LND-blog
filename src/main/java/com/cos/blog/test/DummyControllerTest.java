package com.cos.blog.test;

import com.cos.blog.domain.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable Long id) {
        /* Optional 쓰는 이유
        user3만 있고 user4는 없네 user 4를 찾으면 CB에서 못찾으니까 null이 될거야
        그럼 return null이 되니까 Optional로 너의 User 객체를 감싸서 가져올게
        개발자 너가 Null인지 아닌지 판단해서 return해줘
        */

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 사용자는 없습니다");
        });

        /*
        요청 : 웹브라우저
        user 객체 = 자바 오브젝트
        변환 (웹브라우저가 이해할 수 있는 데이터) ->json
        스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
        만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
        user 오브젝트를 json으로 변환해서 브라우저에게 던져준다
        */
        return user;
    }
}
