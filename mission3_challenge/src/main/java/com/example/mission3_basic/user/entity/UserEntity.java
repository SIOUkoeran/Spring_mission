package com.example.mission3_basic.user.entity;


import com.example.mission3_basic.Area.entity.AreaEntity;
import com.example.mission3_basic.board.entity.BoardEntity;
import com.example.mission3_basic.post.entity.PostEntity;
import com.example.mission3_basic.review.entity.ShopReviewEntity;
import com.example.mission3_basic.shop.entity.ShopEntity;
import com.example.mission3_basic.user.dto.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    private String username;

    private String password;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_authority",joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private List<AuthorityEntity> authorities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<BoardEntity> boards = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<PostEntity> posts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<ShopEntity> shops = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "writer")
    private List<ShopReviewEntity> reviews = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Enumerated(EnumType.STRING)
    private UserInfo userInfo;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<AreaEntity> area = new ArrayList<>();

    @Builder
    public UserEntity(User.RequestUser requestUser, AreaEntity area){
        this.username  = requestUser.getUsername();
        this.userInfo = requestUser.getUserInfo();
        this.userStatus = UserStatus.CREATED;
    }

    @Builder(builderClassName = "login", builderMethodName = "login")
    public UserEntity(String username, String password, UserInfo userinfo, AuthorityEntity authority){
        this.username = username;
        this.password = password;
        this.userInfo = userinfo;
        this.authorities = Arrays.asList(authority);
    }

}
