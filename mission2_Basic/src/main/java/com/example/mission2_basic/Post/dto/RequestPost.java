package com.example.mission2_basic.Post.dto;


import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RequestPost {

    private Long id;

    @NonNull
    private String title;

    @NotNull(message = "작성자는 필수입니다.")
    private String writer;

    @NotNull(message = "비밀번호는 필수입니다.")
    private String password;

    @NotNull(message = "내용은 필수입니다.")
    private String content;

    private Long boardId;

    public RequestPost(@NonNull String title, String writer, String password, String content) {
        this.title = title;
        this.writer = writer;
        this.password = password;
        this.content = content;
    }
    public RequestPost(Long id, @NonNull String title, String writer, String password, String content) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.password = password;
        this.content = content;
    }
    public RequestPost(Long id, Long boardId, @NonNull String title, String writer, String password, String content) {
        this.id = id;
        this.boardId = boardId;
        this.title = title;
        this.writer = writer;
        this.password = password;
        this.content = content;
    }

    @Override
    public String toString() {
        return "RequestPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", password='" + password + '\'' +
                ", content='" + content + '\'' +
                ", boardId=" + boardId +
                '}';
    }
}
