package dev.aquashdw.community.exception.exceptionHandler;


import dev.aquashdw.community.dto.common.ErrorCode;
import dev.aquashdw.community.dto.common.response.ErrorResponse;
import dev.aquashdw.community.exception.NotEqualUserSignUpPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class SecurityExceptionHandler {

    /**
     * 로그인 실패시 에러 핸들러
     * @param e
     * @return ErrorCode.FAILED_LOGIN
     */
    @ExceptionHandler(value = {AuthenticationException.class})
    @ResponseStatus(HttpStatus.OK)
    protected ErrorResponse handleLoginFailed(AuthenticationException e){
        log.info("로그인 실패");
        return new ErrorResponse(ErrorCode.FAILED_LOGIN.getStatus(), ErrorCode.FAILED_LOGIN.getMessage());
    }

    /**
     * 회원가입시 비밀번호 불일치시 에러 핸들러
     * @param e
     * @return ErrorDCode.NOT_EQUAL_USER_PASSWORD
     */
    @ExceptionHandler(value = NotEqualUserSignUpPasswordException.class)
    @ResponseStatus(HttpStatus.OK)
    protected ErrorResponse handleNotEqualPassword(NotEqualUserSignUpPasswordException e){
        log.info("회원가입 비밀번호 실패");
        return new ErrorResponse(ErrorCode.NOT_EQUAL_USER_PASSWORD.getStatus(), ErrorCode.NOT_EQUAL_USER_PASSWORD.getMessage());
    }
}
