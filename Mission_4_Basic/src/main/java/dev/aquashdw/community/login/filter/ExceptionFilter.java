package dev.aquashdw.community.login.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.aquashdw.community.dto.common.ErrorCode;
import dev.aquashdw.community.dto.common.response.ErrorResponse;
import dev.aquashdw.community.exception.CustomUnsupportedJwtException;
import dev.aquashdw.community.exception.ExpiredTokenException;
import dev.aquashdw.community.exception.InvalidSignatureException;
import dev.aquashdw.community.exception.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * jwt exception filter
 */
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
