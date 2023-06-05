package com.study.jpa.chap05_practice.service;

import com.study.jpa.chap05_practice.dto.*;
import com.study.jpa.chap05_practice.entity.HashTag;
import com.study.jpa.chap05_practice.entity.Post;
import com.study.jpa.chap05_practice.repository.HashTagRepository;
import com.study.jpa.chap05_practice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional // jpa레파지토리는 반드시 트랜잭션 아노테이션 필수
public class PostService {

    private final PostRepository postRepository;

    private final HashTagRepository hashTagRepository;


    public PostListResponseDTO getPosts(PageDTO dto) {
        //Pageable 객체 생성
        Pageable pageable = PageRequest.of(dto.getPage() -1 , dto.getSize(), Sort.by("createDate").descending());
        //데이터베이스에서 목록 조회
        Page<Post> posts = postRepository.findAll(pageable);

        //게시물 정보만 꺼내오기
        List<Post> postList = posts.getContent();

        List<PostDetailResponseDTO> detaillist = postList.stream().map(post -> new PostDetailResponseDTO(post)).collect(Collectors.toList());

        //DB에서 조회한 정보를 JSON형태에 맞는 DTO로 변환
        //총 게시물 수가 아니라 조회된 개수
        PostListResponseDTO responseDTO = PostListResponseDTO.builder().count(detaillist.size()).pageInfo(new PageResponseDTO(posts)).posts(detaillist).build();

        return responseDTO;
    }

    public PostDetailResponseDTO getDetail(Long id) {

        Post postEntity = getPost(id);
        return new PostDetailResponseDTO(postEntity);
    }

    private Post getPost(Long id) {
        Post postEntity = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException(
                        id + "번 게시물이 존재하지않습니다 !"
                )
        );
        return postEntity;
    }

    public PostDetailResponseDTO insert(final PostCreateDTO dto) throws RuntimeException{

        //게시물 저장
        Post save = postRepository.save(dto.toEntity());

        //해시 태그 저장
        List<String> hashTags = dto.getHashTags();
        if(hashTags!=null && hashTags.size()>0){
            hashTags.forEach(ht->{
                HashTag saved = hashTagRepository.save(HashTag.builder().tagName(ht).post(save).build()
                );

                save.addHashTag(saved);
            });

        }
        return new PostDetailResponseDTO(save);
    }

    //게시물 수정
    public PostDetailResponseDTO modify(final PostModifyDTO dto) {

        //수정 전 데이터 조회
        Post postEntity = getPost(dto.getPostNo());

        //수정시작
        postEntity.setTitle(dto.getTitle());
        postEntity.setContent(dto.getContent());

        //수정완료
        Post modifiedPost = postRepository.save(postEntity);

        return new PostDetailResponseDTO(modifiedPost);
    }

    //삭제
    public void delete(Long id) {

        postRepository.deleteById(id);
    }
}
