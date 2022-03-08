package com.example.mission3_basic.Area;

import com.example.mission3_basic.Area.dto.Area;
import com.example.mission3_basic.Area.entity.AreaEntity;
import com.example.mission3_basic.user.dto.User;
import com.example.mission3_basic.user.entity.UserEntity;

import java.util.List;

public interface AreaService {
    AreaEntity createAreaInSignUp(User.RequestUser request, UserEntity user);
    List<Area.Response> findAreaByUser(UserEntity user);

    Area.Response createArea(Area.Request request, UserEntity userReturnEntity);

    Area.Response updateArea(UserEntity userReturnEntity, Area.Request request, Long areaId);
    AreaEntity findAreaReturnEntity(Long areaId);
    void deleteArea(Long areaId, UserEntity user);
}
