package com.be1.plant4you.domain;

import com.be1.plant4you.common.BaseTimeEntity;
import com.be1.plant4you.domain.board.Comment;
import com.be1.plant4you.domain.board.Likes;
import com.be1.plant4you.domain.board.Post;
import com.be1.plant4you.domain.board.Scrap;
import com.be1.plant4you.enumerate.Provider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Comment> commentList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Likes> likesList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Scrap> scrapList = new ArrayList<>();

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String nickName;

    private String imgUrl;

    @Enumerated(value = STRING)
    @Column(nullable = false)
    private Provider provider;

    @Column(length = 50)
    private String providerId;
}