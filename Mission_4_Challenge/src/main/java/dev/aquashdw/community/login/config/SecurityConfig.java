package dev.aquashdw.community.login.config;


import dev.aquashdw.community.entity.user.UserInfo;
import dev.aquashdw.community.login.jwt.JwtAccessDeniedHandler;
import dev.aquashdw.community.login.jwt.JwtAuthenticationEntryPoint;
import dev.aquashdw.community.login.jwt.JwtProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(JwtProvider jwtProvider, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeninedHandler) {
        this.jwtProvider = jwtProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeninedHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().loginPage("/user/login")
                .and()
                .authorizeRequests()
                .antMatchers("/user/signin", "/user/signup", "/token/refresh", "/user/login").permitAll()
                .antMatchers(HttpMethod.POST, "/shop/**").hasRole(String.valueOf(UserInfo.SHOP_OWNER))
                .antMatchers(HttpMethod.PUT, "/shop/**").hasRole(String.valueOf(UserInfo.SHOP_OWNER))
                .antMatchers(HttpMethod.DELETE, "/shop/**").hasRole(String.valueOf(UserInfo.SHOP_OWNER))
                .anyRequest().authenticated()
                .and()
                .apply(new JwtSecurityConfig(jwtProvider));
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
