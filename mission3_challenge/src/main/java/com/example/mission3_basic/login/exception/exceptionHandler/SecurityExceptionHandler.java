package com.example.mission3_basic.login.exception.exceptionHandler;


import com.example.mission3_basic.common.ErrorCode;
import com.example.mission3_basic.common.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class SecurityExceptionHandler {

    @ExceptionHandler(value = {AuthenticationException.class})
    @ResponseStatus(HttpStatus.OK)
    protected ErrorResponse handleLoginFailed(AuthenticationException e){
        log.info("로그인 실패");
        return new ErrorResponse(ErrorCode.FAILED_LOGIN.getStatus(), ErrorCode.FAILED_LOGIN.getMessage());
    }
}
