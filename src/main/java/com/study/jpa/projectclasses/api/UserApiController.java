package com.study.jpa.projectclasses.api;

import com.study.jpa.projectclasses.dto.UserRegisterRequestDTO;
import com.study.jpa.projectclasses.service.UserService;
import com.study.jpa.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserApiController {


    @Value("${file.upload.root-path}")
    private String rootPath;
    private final UserService userService;

    //회원가입 요청
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequestDTO dto){

        if (dto==null){
            return ResponseEntity.badRequest().body("회원정보를 제대로 입력해주세요");
        }

        log.info("/user/register POST! --{}",dto);



//        String savePath = null;
//        if (!profileImage.isEmpty()) {
//            savePath = FileUtil.uploadFile(dto.getProfileImage(), rootPath);
//        }

        boolean join = userService.join(dto);

        if (join){
            return ResponseEntity.ok().body(join);
        }else{
            return ResponseEntity.internalServerError().body("회원가입에 실패했습니다");
        }
    }
}
