package dev.aquashdw.community.dto.common.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseObject {
    private String code;
    private String message;
    private Object data;

    @Builder
    public ResponseObject(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
