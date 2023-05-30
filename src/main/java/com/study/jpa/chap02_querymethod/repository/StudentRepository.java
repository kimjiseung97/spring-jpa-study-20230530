package com.study.jpa.chap02_querymethod.repository;

import com.study.jpa.chap02_querymethod.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    List<Student> findByName(String name);
    List<Student> findByCityAndMajor(String city, String major);


    List<Student> findByMajorContaining(String major);  //likequery

    //네이티브 쿼리 사용
    @Query(value = "SELECT * FROM tbl_student WHERE stu_name = :nm",nativeQuery = true)
    Student findNameWithSQL(@Param("nm") String name);

    //JPQL
    // select 별칭 from 엔터티 클래스명 as 별칭
    // where 별칭.필드명 = ?

    // native-sql: SELECT * FROM tbl_student
    //             WHERE stu_name = ?

    //jpql : SELECT st FROM Student as st
    //       WHERE st.name = ?

    //도시 이름으로 학생 조회
    //@Query(value = "SELECT * FROM tbl_student WHERE city = ?1",nativeQuery = true) //? 숫자 하면 순서대로 자동매칭
    @Query("SELECT s FROM Student s WHERE s.city = ?1")
    Student getByCityWithJPQL(String city);

    @Query("SELECT st FROM Student st WHERE st.name LIKE %:nm%") //실무적 측면에서는 @Param을 해주는 것이 좋다
    List<Student> searchByNamesWithJPQL(@Param("nm") String name);


    //JPQL로 수정 삭제 쿼리쓰기
    @Modifying // 조회가 아닐 경우 무조건 붙여야 함
    @Query("DELETE FROM Student s WHERE s.name = ?1")
    void deleteByNameWithJPQL(String name);
}
