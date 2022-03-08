package com.example.mission3_basic.common;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_PARAMETER(4000, "Invalid Request Data"),
    INVALID_OAUTH(4000, "oauth login을 먼저 해 주세요"),
    NOT_FOUND_USER(4040, "user를 찾을 수 없습니다."),
    NOT_FOUND_PROJECT(4040, "project를 찾을 수 없습니다."),
    USER_DUPLICATE(4000, "user가 중복되었습니다."),



    ACCESS_DENIED(4000, "접근이 제한되었습니다."),
    AUTHORIZATION_NOT_FOUND(4030, "Authorization 헤더를 찾을 수 없습니다."),
    NOT_FOUND_REFRESHTOKEN(4040, "token 이 없습니다."),
    EXPIRED_TOKEN(4010, "token 이 만료되었습니다."),
    INVALID_TOKEN(4000,"token이 잘못되었습니다." ),
    INVALID_SIGNATURE(4000, "잘못된 token 서명입니다."),
    FAILED_LOGIN(4010, "로그인을 실패했습니다."),
    UNSUPPORTED_TOKEN(4000, "지원되지않는 token 입니다."),


    NOT_FOUND_BOARD(4040, "게시물을 찾을 수 없습니다."),
    INVALID_WIRTER(4000, "작성자가 일치하지 않습니다."),


    NOT_FOUND_POST(4040, "게시글을 찾을 수 없습니다."),


    NOT_FOUND_AREA(4040, "주소를 찾을 수 없습니다."),


    NOT_FOUND_SHOP(4040, "가게를 찾을 수 없습니다."),
    DUPLICATE_SHOP_NAME(4000, "중복된 가게 이름이 있습니다."),
    NOT_FOUND_REVIEW(4040,"리뷰를 찾을 수 없습니다.");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
