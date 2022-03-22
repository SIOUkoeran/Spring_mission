package dev.aquashdw.community.login.controller;

import dev.aquashdw.community.login.jwt.JwtProvider;
import dev.aquashdw.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserLoginController {

    private final UserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtProvider;

    public UserLoginController(UserService userService, AuthenticationManagerBuilder authenticationManagerBuilder, JwtProvider jwtProvider) {
        this.userService = userService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtProvider = jwtProvider;
    }




//
//    @ResponseStatus(HttpStatus.OK)
//    @PostMapping("/signin")
//    public Response signin(HttpServletResponse response,
//                           User.RequestSignIn requestUserLoginForm){
//
//        log.info("requestLoginForm {}" , requestUserLoginForm.toString() );
//
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(requestUserLoginForm.getUsername(), requestUserLoginForm.getPassword());
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token);
//
//        String jwt = jwtProvider.createToken(authentication);
//        String refreshJwt = jwtProvider.createRefreshToken(authentication);
//
//        response.setHeader("Authorization", "Bearer " + jwt);
//        response.setHeader("refresh_token", "Bearer " + refreshJwt);
//
//        return Response.of()
//                .code("2000")
//                .message("로그인 성공! 토큰이 발행되었습니다.")
//                .data(new TokenDto(jwt, refreshJwt))
//                .build();
//    }
}
