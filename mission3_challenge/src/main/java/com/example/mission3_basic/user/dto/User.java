package com.example.mission3_basic.user.dto;


import com.example.mission3_basic.user.entity.UserInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



public class User {

    @Getter
    @NoArgsConstructor
    public static class RequestUser{
        private String username;
        private UserInfo userInfo;
        private String province;
        private String municipal;
        private String city;
        private String street;
        private String country;
        private String neighborhood;
        private String township;
        private String town;

        @Builder
        public RequestUser(String username, UserInfo userInfo, String province, String municipal, String city, String street, String country, String neighborhood, String township, String town) {
            this.username = username;
            this.userInfo = userInfo;
            this.province = province;
            this.municipal = municipal;
            this.city = city;
            this.street = street;
            this.country = country;
            this.neighborhood = neighborhood;
            this.township = township;
            this.town = town;
        }

        @Override
        public String toString() {
            return "RequestUser{" +
                    "username='" + username + '\'' +
                    ", userInfo=" + userInfo +
                    ", province='" + province + '\'' +
                    ", municipal='" + municipal + '\'' +
                    ", city='" + city + '\'' +
                    ", street='" + street + '\'' +
                    ", country='" + country + '\'' +
                    ", neighborhood='" + neighborhood + '\'' +
                    ", township='" + township + '\'' +
                    ", town='" + town + '\'' +
                    '}';
        }
    }

    @NoArgsConstructor
    @Getter
    public static class ResponseUser{
        private Long userId;
        private String username;

        public ResponseUser(Long userId, String username){
            this.userId = userId;
            this.username = username;
        }

        @Override
        public String toString() {
            return "ResponseUser{" +
                    "userId=" + userId +
                    ", username='" + username + '\'' +
                    '}';
        }
    }
    @Getter
    @NoArgsConstructor
    public static class RequestSignupForm{
        private String username;
        private String password;
        private UserInfo userInfo;

        public RequestSignupForm(String username, String password, UserInfo userInfo) {
            this.username = username;
            this.password = password;
            this.userInfo = userInfo;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class SignUpResponse{
        private Long userId;
        private String username;


        public SignUpResponse(String username, Long userId) {
            this.username = username;
            this.userId = userId;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class RequestUserLoginForm{
        private String username;
        private String password;

        public RequestUserLoginForm(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
}
