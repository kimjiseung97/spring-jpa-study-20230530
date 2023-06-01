package com.study.jpa.chap05_practice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class PageDTO {

    //페이지 장
    private int page;
    //한페이지당 양
    private int size;

    public PageDTO(){
        this.page = 1;
        this.size = 10;
    }
}
