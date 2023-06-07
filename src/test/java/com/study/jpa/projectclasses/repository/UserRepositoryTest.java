package com.study.jpa.projectclasses.repository;

import com.study.jpa.projectclasses.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(value = false)
class UserRepositoryTest {

    @Autowired
    UserRepository repository;
    @Test
    @DisplayName("유저의 정보가 저장되어야한다")
    void saveUserTest() throws ParseException {
        //given
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date userBirth;
        userBirth = dateFormat.parse("1997-01-18");
        User user = User.builder()
                .userAddress("호평동, 남양주, 경기도")
                .userEmail("dickseung23@naver.com")
                .userBirth(userBirth)
                .userFullAddress("경기도 남양주 호평동 늘을1로 107")
                .userGrade("user")
                .userPhone("010-7455-8101")
                .userPoint(100)
                .userPassword("kimjs12000")
                .userName("김지승")
                .build();
        //when
        repository.save(user);
        //then
    }
}