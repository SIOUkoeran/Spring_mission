package com.example.mission3_basic.Area;

import com.example.mission3_basic.Area.entity.AreaEntity;
import com.example.mission3_basic.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaRepository extends JpaRepository<AreaEntity, Long> {
    Optional<AreaEntity> findByUser(UserEntity user);

}
