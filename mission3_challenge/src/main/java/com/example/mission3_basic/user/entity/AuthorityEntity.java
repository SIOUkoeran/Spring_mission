package com.example.mission3_basic.user.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "authority")
public class AuthorityEntity {

    @Column(name = "authority_name")
    @Id
    private String authorityName;

//    @OneToMany(mappedBy = "authority")
//    private List<UserAuthorityEntity> userAuthorityEntity;

    public AuthorityEntity(String authorityName) {
        this.authorityName = authorityName;
    }
}
