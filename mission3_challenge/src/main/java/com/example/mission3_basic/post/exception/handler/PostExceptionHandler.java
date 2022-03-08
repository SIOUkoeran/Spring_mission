package com.example.mission3_basic.post.exception.handler;

import com.example.mission3_basic.common.response.ErrorResponse;
import com.example.mission3_basic.post.exception.NotFoundPostException;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostExceptionHandler {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NotFoundPostException.class)
    protected ErrorResponse handleNorFoundPostException(NotFoundPostException e){
        return new ErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().getMessage());
    }
}
