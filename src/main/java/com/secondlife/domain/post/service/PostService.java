package com.secondlife.domain.post.service;

import com.secondlife.domain.global.service.S3UploadService;
import com.secondlife.domain.post.dto.request.PostRegistRequestDto;
import com.secondlife.domain.post.dto.request.PostUpdateRequestDto;
import com.secondlife.domain.post.dto.response.PostDetailResponseDto;
import com.secondlife.domain.post.dto.response.PostListResponseDto;
import com.secondlife.domain.post.entity.Post;
import com.secondlife.domain.post.repository.PostRepository;
import com.secondlife.domain.user.entity.User;
import com.secondlife.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final S3UploadService s3UploadService;

    // Id로 Post 조회
    public PostDetailResponseDto findPostById(Long id) {

        Post findPost = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 post가 존재하지 않습니다."));

        return new PostDetailResponseDto(findPost);
    }

    // 전체 post 조회
    public List<PostListResponseDto> findPostList() {

        List<PostListResponseDto> findPostListResponseDtoList = new ArrayList<>();

        List<Post> findPostList = postRepository.findAll();

        for (Post post : findPostList)
            findPostListResponseDtoList.add(new PostListResponseDto(post));

        return findPostListResponseDtoList;
    }

    // post 등록
    @Transactional
    public Long registPost(Long userId, PostRegistRequestDto postRegistRequestDto) {

        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 user가 존재하지 않습니다."));

        postRegistRequestDto.setUser(findUser);

        Post registPost = postRegistRequestDto.toEntity();

        postRepository.save(registPost);

        return registPost.getId();
    }

    // post 등록 + 사진
    @Transactional
    public Long registPostWithImg(Long userId, PostRegistRequestDto postRegistRequestDto, MultipartFile multipartFile) throws IOException {

        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 user가 존재하지 않습니다."));

        postRegistRequestDto.setUser(findUser);

        String profile = s3UploadService.upload(multipartFile);

        postRegistRequestDto.setURL(profile);

        Post registPost = postRegistRequestDto.toEntity();

        postRepository.save(registPost);

        return registPost.getId();
    }

    // post 수정
    @Transactional
    public Long updatePost(Long id, PostUpdateRequestDto postUpdateRequestDto) {

        Post findPost = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 post가 존재하지 않습니다."));

         return findPost.update(postUpdateRequestDto);
    }

    // post 삭제
    @Transactional
    public Long deletePost(Long id) {

        postRepository.deleteById(id);

        return id;
    }
}
