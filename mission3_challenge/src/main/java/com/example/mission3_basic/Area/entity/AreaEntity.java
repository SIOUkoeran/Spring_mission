package com.example.mission3_basic.Area.entity;


import com.example.mission3_basic.Area.dto.Area;
import com.example.mission3_basic.Area.entity.Address1;
import com.example.mission3_basic.Area.entity.Address2;
import com.example.mission3_basic.Area.entity.Address3;
import com.example.mission3_basic.user.dto.User;
import com.example.mission3_basic.user.entity.UserEntity;
import lombok.*;
import org.locationtech.jts.geom.Point;


import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "area")
public class AreaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long id;

    @Embedded
    private Address1 address1;
    @Embedded
    private Address2 address2;
    @Embedded
    private Address3 address3;

    @Column
    private Point location;

    @Enumerated(EnumType.STRING)
    private AreaStatus areaStatus = AreaStatus.CREATED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Builder
    public AreaEntity(User.RequestUser requestUser, UserEntity user){
        this.address1 = new Address1(requestUser.getProvince(), requestUser.getMunicipal());
        this.address2 = new Address2(requestUser.getCity(), requestUser.getStreet(), requestUser.getCountry());
        this.address3 = new Address3(requestUser.getNeighborhood(), requestUser.getTownship(), requestUser.getTown());
        setUser(user);
    }

    @Builder(builderMethodName = "afterSignup", builderClassName = "afterSignup")
    public AreaEntity(Area.Request request, UserEntity user){
        this.address1 = request.getAddress1();
        this.address2 = request.getAddress2();
        this.address3 = request.getAddress3();
        setUser(user);
    }

    public void setUser(UserEntity user){
        this.user = user;
        user.getArea().add(this);
    }

    public void update(Area.Request request){
        this.address1 = request.getAddress1();
        this.address2 = request.getAddress2();
        this.address3 = request.getAddress3();
    }

    public void delete(){
        this.areaStatus = AreaStatus.DELETED;
    }

    @Override
    public String toString() {
        return "AreaEntity{" +
                "id=" + id +
                ", address1=" + address1 +
                ", address2=" + address2 +
                ", address3=" + address3 +
                ", location=" + location +
                ", user=" + user +
                '}';
    }
}
