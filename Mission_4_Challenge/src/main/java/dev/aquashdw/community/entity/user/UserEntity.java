package dev.aquashdw.community.entity.user;

import dev.aquashdw.community.dto.User;
import dev.aquashdw.community.entity.AreaEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "community_user")
@NoArgsConstructor
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;
    private String password;

    @ManyToOne(
            targetEntity = AreaEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "area_id")
    private AreaEntity residence;

    private Boolean isShopOwner;



    private String authorities;



    public UserEntity(Long id, String username, String password, AreaEntity residence, Boolean isShopOwner) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.residence = residence;
        this.isShopOwner = isShopOwner;
    }

    /**
     * 편의 메소드
     * @param areaEntity
     */
    public void addArea(AreaEntity areaEntity){
        this.residence = areaEntity;
        areaEntity.getUser().add(this);
    }

    /**
     * login Builder 생성자
     * @param requestSignUp
     */
    @Builder(builderClassName = "login", builderMethodName = "login")
    public UserEntity(User.RequestSignUp requestSignUp,String password, UserInfo userInfo){
        this.username = requestSignUp.getUsername();
        this.password = password;
        this.isShopOwner = requestSignUp.getIs_shop_owner();
        this.authorities = String.valueOf(userInfo);
    }



    public void setId(Long id) {
        this.id = id;
    }



    public void setUsername(String username) {
        this.username = username;
    }



    public void setPassword(String password) {
        this.password = password;
    }



    public void setResidence(AreaEntity residence) {
        this.residence = residence;
    }



    public void setShopOwner(Boolean shopOwner) {
        isShopOwner = shopOwner;
    }

    public List<String> getAuthorities(){
        return List.of(this.authorities);
    }
}
