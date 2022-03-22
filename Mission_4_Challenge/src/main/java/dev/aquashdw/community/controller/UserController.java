package dev.aquashdw.community.controller;

import dev.aquashdw.community.controller.dto.UserDto;
import dev.aquashdw.community.dto.User;
import dev.aquashdw.community.dto.common.response.Response;
import dev.aquashdw.community.login.dto.TokenDto;
import dev.aquashdw.community.login.jwt.JwtProvider;
import dev.aquashdw.community.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtProvider;

    public UserController(UserService userService, AuthenticationManagerBuilder authenticationManagerBuilder, JwtProvider jwtProvider) {
        this.userService = userService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(this.userService.createUser(userDto));
    }


    /**
     * 회원가입 컨트롤러 로직
     */

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/signup")
    public String loginForm(Model model){
        model.addAttribute("signup",new User.RequestSignUp());
        return "/signup-form";
    }


    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("signup") User.RequestSignUp signup, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "/signup-form";
        }
        User.ResponseLogin responseLogin = this.userService.signUpUser(signup);
        return "redirect:/user/signin";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/signin")
    public String signin(Model model){
        model.addAttribute("login", new User.RequestSignIn());
        return "/login-form";
    }


    @PostMapping("/signin")
    public @ResponseBody Response signinPost(HttpServletResponse response,
                           User.RequestSignIn requestSignIn){

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(requestSignIn.getUsername(), requestSignIn.getPassword());
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

    @GetMapping("{id}")
    public ResponseEntity<UserDto> readUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userService.readUser(id));
    }

    @GetMapping
    public ResponseEntity<Collection<UserDto>> readUserAll(){
        return ResponseEntity.ok(this.userService.readUserAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto){
        this.userService.updateUser(id, userDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> DeleteUser(@PathVariable("id") Long id){
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
