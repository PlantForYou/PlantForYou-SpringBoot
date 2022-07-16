package com.be1.plant4you.controller;

import com.be1.plant4you.common.CurrentUser;
import com.be1.plant4you.dto.request.PostRequest;
import com.be1.plant4you.dto.request.PostUpdateRequest;
import com.be1.plant4you.dto.response.PostResponse;
import com.be1.plant4you.dto.response.PostShortResponse;
import com.be1.plant4you.enumerate.PostCat;
import com.be1.plant4you.service.PostService;
import com.sun.security.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;

    /**
     * 카테고리로 게시글 리스트 최신순으로 조회
     * , 전체글 최신순 or 인기순으로 리스트 조회
     * , 내가 쓴 게시글 리스트 최신순으로 조회
     */
    @GetMapping
    public List<PostShortResponse> getPosts(@CurrentUser UserPrincipal userPrincipal,
                                            @RequestParam(required = false) PostCat cat,
                                            @RequestParam(required = false) String order) {
        if (cat != null) {
            return postService.getPostsByCat(cat);
        }
        if (order != null) {
            if (order.equals("new")) {
                return postService.getPostsOrderByNew();
            } else {
                return postService.getPostsOrderByLikes();
            }
        }

        Long userId = 0L;
        return postService.getMyPosts(userId);
    }

    /**
     * 게시글 상세내용 조회 (내가 좋아요, 스크랩한 글인지 여부 확인 위해 userId 전달)
     */
    @GetMapping("/{postId}")
    public PostResponse getPost(@CurrentUser UserPrincipal userPrincipal,
                                @PathVariable Long postId) {
        Long userId = 0L;
        return postService.getPost(userId, postId);
    }

    /**
     * 게시글 등록
     */
    @PostMapping
    public String uploadPost(@CurrentUser UserPrincipal userPrincipal,
                             @RequestBody PostRequest postRequest) {
        Long userId = 0L;
        postService.upload(userId, postRequest);
        return "글 등록이 완료되었습니다.";
    }

    /**
     * 게시글 수정
     */
    @PutMapping("/{postId}")
    public String modifyPost(@CurrentUser UserPrincipal userPrincipal,
                             @PathVariable Long postId,
                             @RequestBody PostUpdateRequest postUpdateRequest) {
        Long userId = 0L;
        postService.modify(userId, postId, postUpdateRequest);
        return "글 수정이 완료되었습니다.";
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/{postId}")
    public String deletePost(@CurrentUser UserPrincipal userPrincipal,
                             @PathVariable Long postId) {
        Long userId = 0L;
        postService.delete(userId, postId);
        return "글 삭제가 완료되었습니다.";
    }

    /**
     * 내가 댓글, 대댓글 단 게시글 리스트 최근 작성 순으로 조회
     */
    @GetMapping("/cmt")
    public List<PostShortResponse> getMyCmtPosts(@CurrentUser UserPrincipal userPrincipal) {
        Long userId = 0L;
        return postService.getMyCmtPosts(userId);
    }

    /**
     * 내가 좋아요한 게시글 리스트 최신 좋아요 순으로 조회
     */
    @GetMapping("/likes")
    public List<PostShortResponse> getMyLikesPosts(@CurrentUser UserPrincipal userPrincipal) {
        Long userId = 0L;
        return postService.getMyLikesPosts(userId);
    }

    /**
     * 내가 스크랩한 게시글 리스트 최신 스크랩 순으로 조회
     */
    @GetMapping("/scrap")
    public List<PostShortResponse> getMyScrapPosts(@CurrentUser UserPrincipal userPrincipal) {
        Long userId = 0L;
        return postService.getMyScrapPosts(userId);
    }

    /**
     * 게시글 좋아요
     */
    @PostMapping("/likes/{postId}")
    public String saveMyLikesPost(@CurrentUser UserPrincipal userPrincipal,
                                  @PathVariable Long postId) {
        Long userId = 0L;
        postService.saveLikesPost(userId, postId);
        return "게시글을 좋아요 하였습니다.";
    }

    /**
     * 게시글 스크랩
     */
    @PostMapping("/scrap/{postId}")
    public String saveMyScrapPost(@CurrentUser UserPrincipal userPrincipal,
                                  @PathVariable Long postId) {
        Long userId = 0L;
        postService.saveScrapPost(userId, postId);
        return "게시글을 스크랩하였습니다.";
    }

    /**
     * 게시글 좋아요 취소
     */
    @DeleteMapping("/likes/{postId}")
    public String deleteMyLikesPost(@CurrentUser UserPrincipal userPrincipal,
                                    @PathVariable Long postId) {
        Long userId = 0L;
        postService.deleteLikesPost(userId, postId);
        return "게시글 좋아요를 취소하였습니다.";
    }

    /**
     * 게시글 스크랩 취소
     */
    @DeleteMapping("/scrap/{postId}")
    public String deleteMyScrapPost(@CurrentUser UserPrincipal userPrincipal,
                                    @PathVariable Long postId) {
        Long userId = 0L;
        postService.deleteScrapPost(userId, postId);
        return "게시글 스크랩을 취소하였습니다.";
    }
}