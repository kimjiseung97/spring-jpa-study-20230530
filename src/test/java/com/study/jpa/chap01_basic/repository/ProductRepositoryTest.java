package com.study.jpa.chap01_basic.repository;

import com.study.jpa.chap01_basic.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.study.jpa.chap01_basic.entity.Product.Category.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Rollback(false) //실무에서는 true로 해놓는다
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach //테스트 돌리기 전에 실행
    void insertDummyData(){
        //given
        Product p1 = Product.builder().name("아이폰").category(ELECTRONIC).price(10000000).build();
        Product p2 = Product.builder().name("구두").category(FASHION).price(10000000).build();
        Product p3 = Product.builder().name("김밥").category(FOOD ).price(10000000).build();
        Product p4 = Product.builder().name("라면").category(FOOD).price(10000000).build();


        //when
        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);
    }
    @Test
    @DisplayName("5번째 상품을 데이터베이스에  저장해야한다")
    void testSave() {
        Product product = Product.builder().name("정장").price(1200000).category(FASHION).build();

        Product saved = productRepository.save(product);

        assertNotNull(saved);
    }

    @Test
    @DisplayName("id가 2번인 데이터를 삭제해야한다")
    void testRemove() {
        //given
        long id = 2L;
        //when
        productRepository.deleteById(id);
        //then
    }

    @Test
    @DisplayName("상품 전체조회를하면 상품의 개수가 4개여야 한다")
    void testFindAll() {
        //given

        //when
        List<Product> products = productRepository.findAll();
        //then
        products.forEach(System.out::println);

        assertEquals(4,products.size());

    }

    @Test
    @DisplayName("3번 상품을 조회하면 상품명이 '김밥' 여아한다")
    void testFindOne() {
        //given
        Long id = 3L;
        //when
        Optional<Product> product = productRepository.findById(id);

        //then
        product.ifPresent(product1 -> {
            assertEquals("김밥",product1.getName());
        });
        
        Product foundProduct = product.get();
        assertNotNull(foundProduct);

        System.out.println("foundProduct = " + foundProduct);
    }

    @Test
    @DisplayName("2번 상품의 이름과 가격을 변경해야한다")
    void testModify() {
        //given
        long id = 2L;
        String newName = "짜장면";
        int newPrice = 6000;
        //when
        //jpa에서 update는 따로 메서드를 지원하지않고
        //조회한 후 setter로 변경하면 자동으로 update문이 나간다

        Optional<Product> product = productRepository.findById(2L);
        product.ifPresent(product1 -> {
            product1.setName(newName);
            product1.setPrice(newPrice);

            productRepository.save(product1);
        });
        //then
        assertTrue(product.isPresent());

        Product p = product.get();
        assertEquals("짜장면",p.getName());
    }
}