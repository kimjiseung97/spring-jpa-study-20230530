package com.study.jpa.chap02_querymethod.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Setter // 실무적 측면에서 setter는 신중히 만들것 객체불변성이 깨질 수 있기 때문에
@Getter
@ToString
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_student")
@Builder
public class Student {

    @Id
    @Column(name = "stu_id")
    @GeneratedValue(generator = "uid")
    @GenericGenerator(strategy = "uuid", name="uid")
    private String id;

    @Column(name = "stu_name", nullable = false)
    private String name;

    private String city;

    private String major;




}
