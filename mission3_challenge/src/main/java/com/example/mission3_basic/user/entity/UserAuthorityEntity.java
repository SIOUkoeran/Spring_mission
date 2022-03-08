package com.example.mission3_basic.user.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@Table(name = "user_authority")
public class UserAuthorityEntity {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority_name")
    private AuthorityEntity authority;
}
