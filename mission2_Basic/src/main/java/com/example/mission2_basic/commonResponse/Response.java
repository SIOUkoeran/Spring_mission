package com.example.mission2_basic.commonResponse;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response{
    private String code;
    private Object data;
    private String message;

    public Response(String code, String message, Object data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
    public Response(String code, String message){
        this.code = code;
        this.message = message;
    }

}
