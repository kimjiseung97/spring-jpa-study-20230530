package com.study.jpa.chap04_relation.repository;

import com.study.jpa.chap04_relation.entity.Department;
import com.study.jpa.chap04_relation.entity.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository  departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @DisplayName("특정 부서를 조회하면 해당 부서원들도 함께 조회되어야한다")
    void testFindDept() {
        //given
        Long id = 2L;
        //when
        Department department = departmentRepository.findById(id).orElseThrow();
        //then
        System.out.println("\n\n\n");
        System.out.println("department = " + department.getEmployees());
        System.out.println("\n\n\n");

    }

    @Test
    @DisplayName("Lazy로딩과 Eager로딩의 차이")
    void testLazyEager() {
        //3번 사원을 조회하고싶은데 부서정보는 필요없다
        //given
        Long id = 3L;
        //when
        Employee employee = employeeRepository.findById(id).orElseThrow();
        //then
        System.out.println("\n\n\n");
        System.out.println("employee = " + employee.getDepartment());
        System.out.println("\n\n\n");
    }

    @Test
    @DisplayName("양방향 연관관계에서 연관데이터의 수정")
    void testChangeDept() {
        //3번 사원의 부서를 2번부서에서 1번부서로 변경시켜야한다
        //given
        Employee foundEmp = employeeRepository.findById(3L).orElseThrow();

        Department department = departmentRepository.findById(1L).orElseThrow();

        foundEmp.setDepartment(department);
        employeeRepository.save(foundEmp);
        //when

        // 1번 부서 정보를 조회해서 모든 사원을 보겠다
        Department founddepartment = departmentRepository.findById(1L).orElseThrow();
        System.out.println("\n\n\n");
        founddepartment.getEmployees().forEach(System.out::println);
        System.out.println("\n\n\n");
        //then
    }
}