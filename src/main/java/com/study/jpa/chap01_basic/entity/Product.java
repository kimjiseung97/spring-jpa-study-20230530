package com.study.jpa.chap01_basic.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "tbl_product")
public class Product {

    @Id //pk 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    @Column(name = "prod_id")
    private long id;

    @Column(name = "prod_nm", nullable = false, length = 30)
    private String name;

    @Builder.Default
    private int price  = 0;

    @Enumerated(EnumType.STRING) //enum타입 설정
    @Column(length = 30) //길이 설정
    private Category category;

    @CreationTimestamp //등록시간 자동 삽입
    @Column(updatable = false) //등록시간 수정불가능
    private LocalDateTime createdDate;

    @UpdateTimestamp //업데이트 시간 자동저장
    private LocalDateTime updatedDate;
    public enum Category{
        FOOD,FASHION,ELECTRONIC
    }
}
