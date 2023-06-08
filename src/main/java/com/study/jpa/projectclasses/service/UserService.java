package com.study.jpa.projectclasses.service;

import com.study.jpa.projectclasses.dto.UserRegisterRequestDTO;
import com.study.jpa.projectclasses.entity.User;
import com.study.jpa.projectclasses.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;


    //회원가입 기능
    public boolean join(final UserRegisterRequestDTO dto) {

        //dto를 엔티티로 변환
        User user = User.builder()
                .userName(dto.getUsername())
                .userPassword(dto.getUserPassword())
                .userPhone(dto.getUserPhone())
                .userAddress(dto.getUserAddress())
                .userEmail(dto.getUserEmail())
                .userFullAddress(dto.getUserFullAddress())
                .userPoint(100)
                .userBirth(dto.getUserBirth())
//                .userImage(savePath)
                .build();

        //엔티티를 저장하고 리턴값 반환
        User saveuser = userRepository.save(user);

        //리턴값이 비어있지않다면 회원가입성공
        //비어있다면 회원가입 실패
        if(saveuser!=null){
            return true;
        }else{
            return false;
        }

    }
}
