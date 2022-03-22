package dev.aquashdw.community.entity;

import dev.aquashdw.community.controller.dto.AreaDto;
import dev.aquashdw.community.entity.user.UserEntity;
import dev.aquashdw.community.login.constant.AreaConstant;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "area")
@NoArgsConstructor
@Getter
public class AreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long id;

    @Column(name = "region_1")
    private String regionMajor;
    @Column(name = "region_2")
    private String regionMinor;
    @Column(name = "region_3")
    private String regionPatch;

    private Double latitude;
    private Double longitude;

    @OneToMany(mappedBy = "residence")
    private List<UserEntity> user = new ArrayList<>();

    public AreaEntity(Long id, String regionMajor, String regionMinor, String regionPatch, Double latitude, Double longitude) {
        this.id = id;
        this.regionMajor = regionMajor;
        this.regionMinor = regionMinor;
        this.regionPatch = regionPatch;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Builder
    public AreaEntity(AreaConstant areaConstant){
        this.regionMajor = areaConstant.getMajor();
        this.regionMinor = areaConstant.getMinor();
        this.regionPatch = areaConstant.getPath();
        this.latitude = areaConstant.getLatitude();
        this.longitude = areaConstant.getLongitude();
    }


    @Override
    public String toString() {
        return "AreaEntity{" +
                "id=" + id +
                ", regionMajor='" + regionMajor + '\'' +
                ", regionMinor='" + regionMinor + '\'' +
                ", regionPatch='" + regionPatch + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", user=" + user +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }



    public void setRegionMajor(String regionMajor) {
        this.regionMajor = regionMajor;
    }


    public void setRegionMinor(String regionMinor) {
        this.regionMinor = regionMinor;
    }


    public void setRegionPatch(String regionPatch) {
        this.regionPatch = regionPatch;
    }


    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }


    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
