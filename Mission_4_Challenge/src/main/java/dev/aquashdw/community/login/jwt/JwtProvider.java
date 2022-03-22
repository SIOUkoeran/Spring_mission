package dev.aquashdw.community.login.jwt;


import dev.aquashdw.community.login.exception.CustomUnsupportedJwtException;
import dev.aquashdw.community.login.exception.ExpiredTokenException;
import dev.aquashdw.community.login.exception.InvalidSignatureException;
import dev.aquashdw.community.login.exception.InvalidTokenException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    @Value("${auth.jwt.secretkey}")
    private String secret;
    @Value("${auth.jwt.expireMills}")
    private String tokenValidityMills;
    @Value("${auth.jwt.refreshExpireMills}")
    private String refreshTokenValidityMills;

    private static final String AUTHORIZATION_HEADER = "Authorization";

    public String createToken(Authentication authentication){
        String authorities = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Date now = new Date();


        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth",authorities)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(new Date(now.getTime() + Long.parseLong(tokenValidityMills)))
                .compact();
    }

    public String createRefreshToken(Authentication authentication){
        String authorities = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Date now = new Date();
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth",authorities)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(new Date(now.getTime() + Long.parseLong(refreshTokenValidityMills)))
                .compact();
    }

    public Authentication getAuthentication(String token){
        Claims claims  = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends SimpleGrantedAuthority> auth =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", auth);
        return new UsernamePasswordAuthenticationToken(principal, token, auth);
    }
    public Boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token).getBody();
            return true;
        }catch (SignatureException e){
            throw new InvalidSignatureException();
        }catch (ExpiredJwtException e){
            throw new ExpiredTokenException();
        }catch (UnsupportedJwtException e){
            throw new CustomUnsupportedJwtException();
        }catch (IllegalArgumentException e){
            throw new InvalidTokenException();
        }catch (SecurityException e){
            throw new InvalidSignatureException();
        }
    }
    public String resolveToken(HttpServletRequest httpRequest){
        String bearerToken = httpRequest.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

}
