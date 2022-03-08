package com.example.mission3_basic.login.filter;

import com.example.mission3_basic.common.ErrorCode;
import com.example.mission3_basic.common.response.ErrorResponse;
import com.example.mission3_basic.login.exception.CustomUnsupportedJwtException;
import com.example.mission3_basic.login.exception.ExpiredTokenException;
import com.example.mission3_basic.login.exception.InvalidSignatureException;
import com.example.mission3_basic.login.exception.InvalidTokenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request,response);
        }catch (InvalidSignatureException e){
            send(response, ErrorCode.INVALID_SIGNATURE);
        }catch (ExpiredTokenException e){
            send(response, ErrorCode.EXPIRED_TOKEN);
        }catch (CustomUnsupportedJwtException e){
            send(response, ErrorCode.UNSUPPORTED_TOKEN);
        }catch (InvalidTokenException e){
            send(response, ErrorCode.INVALID_TOKEN);
        }
    }

    private void send(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getStatus(), errorCode.getMessage());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.writeValue(response.getWriter(), errorResponse);

    }
}
