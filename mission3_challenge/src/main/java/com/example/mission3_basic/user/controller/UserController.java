package com.example.mission3_basic.user.controller;

import com.example.mission3_basic.common.response.Response;
import com.example.mission3_basic.user.dto.User;
import com.example.mission3_basic.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Response createUser(@RequestBody User.RequestUser requestUser)
    {
        User.ResponseUser responseUser = this.userService.createUser(requestUser);
        return Response.of()
                .code("201")
                .message("USER_CREATED")
                .data(responseUser)
                .build();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Response getUser(@PathVariable(name = "userId") String userId){
        return Response.of()
                .code("200")
                .message("USER_FOUND")
                .data(this.userService.getUser(userId))
                .build();
    }


}
