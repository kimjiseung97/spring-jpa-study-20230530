package com.study.jpa.chap04_relation.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Entity
// jpa 연관관계 매핑에서는 연관관계 데이터는 ToString에서 제외해야 한다.
@ToString(exclude = {"department"})
@Table(name="tbl_emp")
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;
    @Column(name="emp_name",nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY) // 한 부서에 여러 사람이 가입할 수 있기때문에
    //실무적인 측면에서는 lazy를 무조건 걸어놓고 명시적으로 원하는 정보가 필요할때 넣어주는게 좋다
    //명시적으로 넣어주면 거기에맞춰서 join을 해서 데이터를 가져오기때문
    @JoinColumn(name = "dept_id")
    private Department department;
}
