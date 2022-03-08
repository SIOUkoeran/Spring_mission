package com.example.mission3_basic.Area;

import com.example.mission3_basic.Area.dto.Area;
import com.example.mission3_basic.Area.entity.AreaEntity;
import com.example.mission3_basic.Area.entity.AreaStatus;
import com.example.mission3_basic.Area.exception.NotFoundAreaException;
import com.example.mission3_basic.board.exception.InvalidWriterException;
import com.example.mission3_basic.user.dto.User;
import com.example.mission3_basic.user.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AreaServiceImpl implements AreaService{

    private final AreaRepository areaRepository;

    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Override
    public AreaEntity createAreaInSignUp(User.RequestUser requestUser, UserEntity user) {
        AreaEntity area = AreaEntity.builder()
                .requestUser(requestUser)
                .user(user)
                .build();
        return this.areaRepository.save(area);
    }



    @Override
    @Transactional(readOnly = true)
    public List<Area.Response> findAreaByUser(UserEntity user) {
        return user.getArea().stream()
                .filter(area-> area.getAreaStatus() == AreaStatus.CREATED)
                .map(area -> Area.Response.builder()
                        .area(area)
                        .user(user)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Area.Response createArea(Area.Request request, UserEntity user) {
        log.info("request {}", request);
        AreaEntity area = AreaEntity.afterSignup()
                .request(request)
                .user(user)
                .build();
        AreaEntity savedArea = this.areaRepository.save(area);

        return Area.Response.builder()
                .area(savedArea)
                .user(user)
                .build();
    }

    @Override
    public Area.Response updateArea(UserEntity user, Area.Request request, Long areaId) {
        AreaEntity area = findAreaReturnEntity(areaId);
        area.update(request);
        AreaEntity savedArea = this.areaRepository.save(area);
        return Area.Response.builder()
                .area(savedArea)
                .user(user)
                .build();
    }



    @Override
    @Transactional(readOnly = true)
    public AreaEntity findAreaReturnEntity(Long areaId) {
        AreaEntity area = this.areaRepository.findById(areaId)
                .orElseThrow(NotFoundAreaException::new);

        if (area.getAreaStatus() == AreaStatus.DELETED)
            throw new NotFoundAreaException();
        return area;
    }

    @Override
    public void deleteArea(Long areaId, UserEntity user) {

        AreaEntity area = findAreaReturnEntity(areaId);
        if (!user.getUsername().equals(area.getUser().getUsername()))
            throw new InvalidWriterException();
        area.delete();
    }


}
