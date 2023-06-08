package com.study.jpa.projectclasses.repository;

import com.study.jpa.projectclasses.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

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
        LocalDate date = LocalDate.of(1997, 10, 1);// Creates a LocalDate object with the specified year, month, and day
        User user = User.builder()
                .userAddress("평내동, 남양주, 경기도")
                .userEmail("choshin97@naver.com")
                .userBirth(date)
                .userFullAddress("경기도 남양주 평내동 늘을1로 107")
                .userGrade("user")
                .userPhone("010-4609-7149")
                .userPoint(100)
                .userPassword("shinjo12000")
                .userName("조신형")
                .build();
        //when
        repository.save(user);
        //then
    }
    @Test
    @DisplayName("2번회원의 이름은 김지승이다")
    void findbyIdTest() {
        //given
        int id = 3;
        //when
        User user = repository.findById(id).orElseThrow();

        assertEquals("조신형",user.getUserName());

        System.out.println("user.getUserName() = " + user.getUserName());
        //then
    }
}