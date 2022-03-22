package dev.aquashdw.community.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class User {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class RequestSignUp {

        @Length(min = 5, message = "최소 아이디 길이는 5글자입니다.")
        String username;


        @NotBlank(message = "비밀번호를 입력해주세요")
        @Length(min = 5, message = "최소 비밀번호는 5글자입니다.")
        String password;

        @NotBlank(message = "비밀번호를 입력해주세요")
        @Length(min = 5, message = "최소 비밀번호는 5글자입니다.")
        String password_check;

        Boolean is_shop_owner;

        public RequestSignUp(String username, String password, Boolean is_shop_owner, String password_check) {
            this.username = username;
            this.password = password;
            this.password_check = password_check;
            this.is_shop_owner = is_shop_owner;
        }

        @Override
        public String toString() {
            return "RequestLogin{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", password_check='" + password_check + '\'' +
                    ", is_shop_owner=" + is_shop_owner +
                    '}';
        }
    }


    @Getter
    @Setter
    @NoArgsConstructor
    public static class ResponseLogin{
        private String username;
        private Long userId;

        public ResponseLogin(String username, Long userId) {
            this.username = username;
            this.userId = userId;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class RequestSignIn {
        private String username;
        private String password;

        public RequestSignIn(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public String toString() {
            return "RequestSignIn{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

}
