package com.example.mission3_basic.user.service;



import com.example.mission3_basic.Area.entity.AreaEntity;
import com.example.mission3_basic.Area.AreaService;
import com.example.mission3_basic.board.exception.NotFoundBoardException;
import com.example.mission3_basic.user.repository.UserRepository;
import com.example.mission3_basic.user.dto.User;
import com.example.mission3_basic.user.entity.AuthorityEntity;
import com.example.mission3_basic.user.entity.UserEntity;
import com.example.mission3_basic.user.exception.UserDuplicateException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AreaService areaService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AreaService areaService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.areaService = areaService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User.ResponseUser createUser(User.RequestUser requestUser) {


        UserEntity user = UserEntity.builder()
                .requestUser(requestUser)
                .build();
        AreaEntity area = this.areaService.createAreaInSignUp(requestUser, user);
        UserEntity userEntity = this.userRepository.save(user);
        return new User.ResponseUser(userEntity.getId(), userEntity.getUsername());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getUsers() {
        return this.userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User.ResponseUser getUser(String username) {
        UserEntity user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND"));
        return new User.ResponseUser(user.getId(), user.getUsername());
    }

    @Override
    public UserEntity getUserByIdReturnUserEntity(Long userId) {
        return  this.userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND"));
    }

    @Override
    public UserEntity getUserReturnEntity(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(NotFoundBoardException::new);
    }

    @Override
    public User.SignUpResponse signUpUser(User.RequestSignupForm signupForm) {
        if (this.userRepository.findOneWithAuthoritiesByUsername(signupForm.getUsername()).isPresent()){
            throw new UserDuplicateException();
        }
        AuthorityEntity authority = new AuthorityEntity(String.valueOf(signupForm.getUserInfo()));
        UserEntity user = UserEntity
                .login()
                .username(signupForm.getUsername())
                .password(passwordEncoder.encode(signupForm.getPassword()))
                .userinfo(signupForm.getUserInfo())
                .authority(authority)
                .build();
        UserEntity savedUser = this.userRepository.save(user);
        return new User.SignUpResponse(savedUser.getUsername(), savedUser.getId());
    }


}
