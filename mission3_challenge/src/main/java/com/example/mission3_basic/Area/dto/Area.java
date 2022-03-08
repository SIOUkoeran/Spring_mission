package com.example.mission3_basic.Area.dto;

import com.example.mission3_basic.Area.entity.Address1;
import com.example.mission3_basic.Area.entity.Address2;
import com.example.mission3_basic.Area.entity.Address3;
import com.example.mission3_basic.Area.entity.AreaEntity;
import com.example.mission3_basic.user.dto.User;
import com.example.mission3_basic.user.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class Area {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response{
        private Address1 address1;
        private Address2 address2;
        private Address3 address3;
        private User.ResponseUser user;

        @Builder
        public Response(UserEntity user, AreaEntity area){
            this.address1 = area.getAddress1();
            this.address2 = area.getAddress2();
            this.address3 = area.getAddress3();
            this.user = new User.ResponseUser(user.getId(), user.getUsername());
        }
    }
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request{
        private Address1 address1;
        private Address2 address2;
        private Address3 address3;

    }
}
