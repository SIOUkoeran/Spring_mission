package com.example.mission3_basic.Area;


import com.example.mission3_basic.Area.dto.Area;
import com.example.mission3_basic.common.response.Response;
import com.example.mission3_basic.common.response.ResponseObject;
import com.example.mission3_basic.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/area")
public class AreaController {

    private final UserService userService;
    private final AreaService areaService;

    public AreaController(UserService userService, AreaService areaService) {
        this.userService = userService;
        this.areaService = areaService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject createArea(@AuthenticationPrincipal User user, @RequestBody Area.Request request) {
        return ResponseObject.builder()
                .code("2010")
                .message("CREATE_AREA")
                .data(this.areaService.createArea(request,
                        this.userService.getUserReturnEntity(user.getUsername())))
                .build();

    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject getAreaByUser(@AuthenticationPrincipal User user){

        return ResponseObject.builder()
                .code("2000")
                .message("AREAS_BY_USER")
                .data(this.areaService.findAreaByUser(this.userService.getUserReturnEntity(user.getUsername())))
                .build();


    }

    @PutMapping("/{areaId}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateArea(@AuthenticationPrincipal User user, @PathVariable Long areaId, @RequestBody Area.Request request
    ){

        Area.Response response = this.areaService.updateArea(this.userService.getUserReturnEntity(user.getUsername()), request, areaId);
        return Response.of()
                .code("2010")
                .message("UPDATE_AREA")
                .data(response)
                .build();
    }

    @DeleteMapping("/{areaId}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteArea(@AuthenticationPrincipal User user, @PathVariable Long areaId){
        this.areaService.deleteArea(areaId, this.userService.getUserReturnEntity(user.getUsername()));
        return Response.of()
                .code("2010")
                .message("DELETE_AREA")
                .data(List.of())
                .build();
    }
}
