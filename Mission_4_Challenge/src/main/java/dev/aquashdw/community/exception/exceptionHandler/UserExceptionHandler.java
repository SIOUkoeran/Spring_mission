package dev.aquashdw.community.exception.exceptionHandler;


import dev.aquashdw.community.dto.common.response.ErrorResponse;
import dev.aquashdw.community.exception.UserDuplicateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserDuplicateException.class)
    @ResponseStatus(HttpStatus.OK)
    protected ErrorResponse userDuplicateExceptionHandle(UserDuplicateException e){
        return new ErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().getMessage());
    }
}
