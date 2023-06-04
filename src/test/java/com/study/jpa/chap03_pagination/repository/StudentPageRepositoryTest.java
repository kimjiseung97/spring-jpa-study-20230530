package com.study.jpa.chap03_pagination.repository;

import com.study.jpa.chap02_querymethod.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// 만약에 서비스클래스를 사용한다면 해당 클래스에 붙일 것!
@Transactional// JPA는 I, U ,D 시에 반드시 트랜잭션 처리가 필수
@Rollback(value = true)
class StudentPageRepositoryTest {

    @Autowired
    StudentPageRepository studentPageRepository;
//    @BeforeEach
//    void bulkInsert(){
//        //학생을 147명 저장
//        for (int i = 1; i <=147 ; i++) {
//            Student s = Student.builder()
//                    .name("김파파" + i).city("도시" + i).major("전공" + i).build();
//
//            studentPageRepository.save(s);
//
//        }
//    }

    @Test
    @DisplayName("기본 페이징 테스트")
    void testBasicPagination() {
        //given
        int pageNo = 1;
        int amount = 10;

        //페이지 정보 생성
        //페이지 번호가 zero-basded
        // Sort.Order.desc("name"),Sort.Order.asc("city")
        Pageable pageInfo = PageRequest.of(pageNo - 1, amount,
                Sort.by("name").descending()
                );
        //when
        Page<Student> foundStudents = studentPageRepository.findAll(pageInfo);
        //페이징 완료된 데이터 셋
        List<Student> students = foundStudents.getContent();

        //총 페이지 수
        int totalPages = foundStudents.getTotalPages();

        //총 학생수
        long totalElements = foundStudents.getTotalElements();

        boolean unpaged = foundStudents.getPageable().isUnpaged();
        boolean paged = foundStudents.getPageable().isPaged();

        //이전페이지 이동가능 여부
        Pageable prev = foundStudents.getPageable().previousOrFirst();
        Pageable next = foundStudents.getPageable().next();




        //then
        System.out.println("\n\n\n");
        students.forEach(System.out::println);
        System.out.println("\n\n\n");
        System.out.println("totalElements = " + totalElements);
        System.out.println("unpaged = " + unpaged);
        System.out.println("paged = " + paged);
        System.out.println("totalPages = "+totalPages);
        System.out.println("next = " + next);
        System.out.println("\n\n\n");
        System.out.println("prev = " +prev);
    }

    @Test
    @DisplayName("이름검색 + 페이징")
    void testSearchAndPagination() {
        //given
        int pageNo = 1;
        int size = 10;
        Pageable pageInfo = PageRequest.of(pageNo-1 , size);

        //when
        Page<Student> students = studentPageRepository.findByNameContaining("3", pageInfo);


        //then
        System.out.println("\n\n\n");
        students.getContent().forEach(System.out::println);
        System.out.println("\n\n\n");
    }
}