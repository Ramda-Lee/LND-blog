package com.cos.blog.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class SecurityConfigTest {

    @Test
    public void Hash_암호화() {
        String encPassword = new BCryptPasswordEncoder().encode("1234");
        System.out.println("1234 해쉬 :" + encPassword);

    }

}