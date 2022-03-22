package dev.aquashdw.community.service;

import dev.aquashdw.community.controller.dto.UserDto;
import dev.aquashdw.community.dto.User;
import dev.aquashdw.community.entity.AreaEntity;
import dev.aquashdw.community.entity.user.UserEntity;
import dev.aquashdw.community.entity.user.UserInfo;
import dev.aquashdw.community.login.constant.AreaConstant;
import dev.aquashdw.community.login.constant.AreaMap;
import dev.aquashdw.community.exception.NotEqualUserSignUpPasswordException;
import dev.aquashdw.community.exception.UserDuplicateException;
import dev.aquashdw.community.repository.AreaRepository;
import dev.aquashdw.community.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final AreaRepository areaRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AreaRepository areaRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.areaRepository = areaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(UserDto userDto){
        Optional<AreaEntity> areaEntityOptional = this.areaRepository.findById(userDto.getAreaId());
        if (areaEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        AreaEntity residence = areaEntityOptional.get();

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setShopOwner(userDto.getIsShopOwner());
        userEntity.setResidence(residence);
        userEntity = this.userRepository.save(userEntity);
        return new UserDto(userEntity);
    }

    public UserDto readUser(Long id) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if (userEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return new UserDto(userEntityOptional.get());
    }

    public List<UserDto> readUserAll(){
        List<UserDto> userDtoList = new ArrayList<>();
        this.userRepository.findAll().forEach(userEntity ->
                userDtoList.add(new UserDto(userEntity)));
        return userDtoList;
    }

    public void updateUser(Long id, UserDto dto){
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if (userEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        UserEntity userEntity = userEntityOptional.get();
        userEntity.setPassword(
                dto.getPassword() == null ? userEntity.getPassword() : dto.getPassword()
        );
        userEntity.setShopOwner(
                dto.getIsShopOwner() == null ? userEntity.getIsShopOwner() : dto.getIsShopOwner()
        );

        Optional<AreaEntity> newArea = this.areaRepository.findById(
                dto.getId() == null ? userEntity.getResidence().getId() : dto.getAreaId());

        newArea.ifPresent(userEntity::setResidence);
        userRepository.save(userEntity);
    }

    public void deleteUser(Long id){
        if (!this.userRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        this.userRepository.deleteById(id);
    }

    @Transactional
    public User.ResponseLogin signUpUser(User.RequestSignUp signupForm) {

        if (this.userRepository.findOneWithAuthoritiesByUsername(signupForm.getUsername()).isPresent()){
            throw new UserDuplicateException();
        }
        if (!signupForm.getPassword().equals(signupForm.getPassword_check())){
            throw new NotEqualUserSignUpPasswordException();
        }
        UserInfo userInfo;

        if (signupForm.getIs_shop_owner()){
            userInfo = UserInfo.SHOP_OWNER;
        }else{
            userInfo = UserInfo.CUSTOMER;
        }

        UserEntity user = UserEntity
                .login()
                .requestSignUp(signupForm)
                .password(passwordEncoder.encode(signupForm.getPassword()))
                .userInfo(userInfo)
                .build();

        AreaEntity areaEntity = AreaEntity.builder()
                .areaConstant(returnRandomArea())
                .build();
        this.areaRepository.save(areaEntity);
        user.setResidence(areaEntity);
        UserEntity savedUser = this.userRepository.save(user);
        return new User.ResponseLogin(savedUser.getUsername(), savedUser.getId());
    }

    private AreaConstant returnRandomArea(){
        Random rand = new Random();
        int a = rand.nextInt(AreaMap.map.size()) + 1;
        logger.info("a : {}" , a);
        return AreaMap.map.get(a);
    }
}
