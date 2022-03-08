package com.example.mission3_basic.login.controller;

import com.example.mission3_basic.common.response.Response;
import com.example.mission3_basic.login.dto.TokenDto;
import com.example.mission3_basic.login.jwt.JwtProvider;
import com.example.mission3_basic.user.service.UserService;
import com.example.mission3_basic.user.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/user")
public class UserLoginController {

    private final UserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtProvider;

    public UserLoginController(UserService userService, AuthenticationManagerBuilder authenticationManagerBuilder, JwtProvider jwtProvider) {
        this.userService = userService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtProvider = jwtProvider;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signup")
    public Response signup(@RequestBody User.RequestSignupForm signupForm){
        User.SignUpResponse signUpResponse= this.userService.signUpUser(signupForm);
        return Response.of()
                .code("201")
                .message("USER_SIGNUP_COMPLETE")
                .data(signUpResponse)
                .build();
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signin")
    public Response signin(HttpServletResponse response,
                           @RequestBody User.RequestUserLoginForm requestUserLoginForm){

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(requestUserLoginForm.getUsername(), requestUserLoginForm.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token);

        String jwt = jwtProvider.createToken(authentication);
        String refreshJwt = jwtProvider.createRefreshToken(authentication);

        response.setHeader("Authorization", "Bearer " + jwt);
        response.setHeader("refresh_token", "Bearer " + refreshJwt);

        return Response.of()
                .code("2000")
                .message("로그인 성공! 토큰이 발행되었습니다.")
                .data(new TokenDto(jwt, refreshJwt))
                .build();
    }
}
