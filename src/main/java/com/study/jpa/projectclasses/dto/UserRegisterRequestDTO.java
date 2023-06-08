package com.study.jpa.projectclasses.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.jpa.projectclasses.entity.User;
import lombok.*;
import org.springframework.cglib.core.Local;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterRequestDTO {

    //유저 이메일
    @NotBlank
    @Email
    private String userEmail;

    //유저 비밀번호
    @NotBlank
    private String userPassword;


    //유저 생년월일
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate userBirth;

    //유저 전화번호
    @NotBlank
    private String userPhone;

    //유저 주소(간략)
    @NotBlank
    private String userAddress;

    //유저 상세 주소
    @NotBlank
    private String userFullAddress;

    //유저 이름
    @NotBlank
    private String username;


//    @Nullable
//    //유저 프로필 이미지
//    private MultipartFile profileImage;


}
