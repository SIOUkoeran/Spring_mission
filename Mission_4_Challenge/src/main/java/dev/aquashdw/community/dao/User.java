package dev.aquashdw.community.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class User {

    @Getter
    @NoArgsConstructor
    public static class RequestLogin{
        String username;
        String password;
        String password_check;
        Boolean is_shop_owner;
        public RequestLogin(String username, String password, Boolean is_shop_owner, String password_check) {
            this.username = username;
            this.password = password;
            this.password_check = password_check;
            this.is_shop_owner = is_shop_owner;
        }
    }


    @Getter
    @NoArgsConstructor
    public static class ResponseLogin{
        private String username;
        private Long userId;

        public ResponseLogin(String username, Long userId) {
            this.username = username;
            this.userId = userId;
        }
    }
}
