package com.example.mission3_basic.user;

import com.example.mission3_basic.Area.entity.AreaEntity;
import com.example.mission3_basic.Area.AreaRepository;
import com.example.mission3_basic.Area.AreaService;
import com.example.mission3_basic.user.dto.User;
import com.example.mission3_basic.user.entity.UserInfo;
import com.example.mission3_basic.user.repository.UserRepository;
import com.example.mission3_basic.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import java.util.List;

@SpringBootTest
@Slf4j
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AreaRepository areaRepository;
    @Autowired
    AreaService areaService;

    @BeforeEach
    void init(){
        this.userRepository.deleteAllInBatch();
        this.areaRepository.deleteAllInBatch();
    }

    @Test
    @Transactional
    @DisplayName("유저 생성하기")
    void createUser(){
        String username = "user1";
        UserInfo userInfo = UserInfo.CUSTOMER;
        String province = "seoul";
        String municipal = "gwanak-gu";
        String city = "city";
        String street = "street";
        String country = "country";
        String neighborhood = "neighborhood";
        String township = "township";
        String town = "town";
        User.RequestUser  requestUser = new User.RequestUser(username, userInfo,province,municipal,city,street,country,neighborhood,township,town);
        User.ResponseUser responseUser = this.userService.createUser(requestUser);
        Assertions.assertThat(responseUser).isNotNull();

    }

    @Test
    @Transactional
    @DisplayName("유저 생성 후 해당 유저 찾기")
    void getUser(){
        String username = "user1";
        UserInfo userInfo = UserInfo.CUSTOMER;
        String province = "seoul";
        String municipal = "gwanak-gu";
        String city = "city";
        String street = "street";
        String country = "country";
        String neighborhood = "neighborhood";
        String township = "township";
        String town = "town";
        User.RequestUser  requestUser = new User.RequestUser(username, userInfo,province,municipal,city,street,country,neighborhood,township,town);
        User.ResponseUser responseUser = this.userService.createUser(requestUser);
        User.ResponseUser user = this.userService.getUser("user1");
        Assertions.assertThat(user.getUserId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("찾는 유저가 없을 때 에러값")
    @Transactional
    void getUserThrowUserNotFound(){
        Assertions.assertThatThrownBy(() -> this.userService.getUser("user1"))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @Transactional
    @DisplayName("유저 생성 후 주소값 찾기")
    void findUserArea(){
        String username = "user1";
        UserInfo userInfo = UserInfo.CUSTOMER;
        String province = "seoul";
        String municipal = "gwanak-gu";
        String city = "city";
        String street = "street";
        String country = "country";
        String neighborhood = "neighborhood";
        String township = "township";
        String town = "town";
        User.RequestUser  requestUser = new User.RequestUser(username, userInfo,province,municipal,city,street,country,neighborhood,township,town);
        User.ResponseUser responseUser = this.userService.createUser(requestUser);


    }
}