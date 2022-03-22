package dev.aquashdw.community.entity.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "authority")
public class AuthorityEntity {
    @Column(name = "authority_name")
    @Id
    private String authorityName;

    public AuthorityEntity(String authorityName) {
        this.authorityName = authorityName;
    }
}
