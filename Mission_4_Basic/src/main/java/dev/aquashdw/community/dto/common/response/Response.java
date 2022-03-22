package dev.aquashdw.community.dto.common.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Response {
    private String code;
    private String message;
    private List<Object> data = new ArrayList<>();

    @Builder
    public Response(String code, String message, List<Object> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Builder(builderClassName = "of", builderMethodName = "of")
    public Response(String code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = List.of(data);
    }

}
