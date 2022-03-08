# mission3

## DB Schema

create table post (   </br>
    id bigint not null auto_increment, </br>
    created_at datetime(6), </br>
    deleted_at datetime(6), </br>
    updated_at datetime(6), </br>
    content varchar(255), </br>
    post_status varchar(255), </br>
    title varchar(255), </br>
    writer varchar(255), </br>
    board_id bigint, </br>
    user_id bigint, </br>
    primary key (id) </br>
) </br>

create table shop ( </br>
    shop_id bigint not null,  </br>
    created_at datetime(6), </br>
    deleted_at datetime(6), </br>
    updated_at datetime(6), </br>
    shop_description varchar(255),  </br>
    shop_name varchar(255),  </br>
    shop_status varchar(255),  </br>
    user_id bigint,  </br>
    primary key (shop_id) </br>
)  </br>

create table shop_post ( </br>
    id bigint not null auto_increment, </br>
    created_at datetime(6),  </br>
    deleted_at datetime(6), </br>
    updated_at datetime(6), </br>
    content varchar(255), </br>
    shop_post_status varchar(255), </br>
    title varchar(255), </br> 
    shop_id bigint, </br>
    primary key (id) </br>
) </br>

create table shop_review ( </br>
    id bigint not null auto_increment,  </br>
    created_at datetime(6), </br>
    deleted_at datetime(6), </br>
    updated_at datetime(6), </br>
    review varchar(255), </br>
    review_status varchar(255), </br> 
    title varchar(255), </br>
    shop_post_id bigint, </br>
    shop_id bigint, </br>
    user_id bigint, </br>
    primary key (id) </br>
) </br>

create table user ( </br>
    user_id bigint not null auto_increment, </br>
    password varchar(255), </br>
    user_info varchar(255), </br>
    user_status varchar(255), </br>
    username varchar(255), </br>
    primary key (user_id) </br>
) </br>

create table user_authority ( </br>
    user_id bigint not null, </br>
    authority_name varchar(255) not null </br>
) </br>

alter table area add constraint  foreign key (user_id) references user (user_id) </br>
alter table board add constraint  foreign key (user_id) references user (user_id) </br>
alter table post add constraint  foreign key (board_id) references board (id) </br>
alter table post add constraint  foreign key (user_id) references user (user_id) </br>
alter table shop add constraint  foreign key (user_id) references user (user_id) </br>
alter table shop_post add constraint  foreign key (shop_id) references shop (shop_id) </br>
alter table shop_review add constraint  foreign key (shop_post_id) references shop_post (id) </br>
alter table shop_review add constraint  foreign key (shop_id) references shop (shop_id) </br>
alter table shop_review add constraint  foreign key (user_id) references user (user_id) </br>

</br>
- shop과 user는 1 : N 관계로 한 유저가 여러개의 shop을 가지도록 했습니다.
- 주소 역시 독립적인 테이블로 구성해 유저 테이블과 1 : N 관계를 가지도록 했습니다.

## flow

- 유저 생성(userEntity 에 생성) 후 로그인 -> jwt 발행.
- jwt를 통해 로그인 -> 유저의 권한에 따른 api 접근 제한(shopPostEntity 생성, 수정, 제거 제한)
- CUSTOMER와 RETAILER로 구분
- Point 라이브러리 사용하여 위도 경도 데이터 저장 가능.
- 그 외 주어진 조건 만족

- 로그인 예
```javascript
{
"code": "201",
"message": "USER_SIGNUP_COMPLETE",
"data": [
{
"userId": 1,
"username": "alstn"
}
]
}
```

```javascript
{
    "code": "2000",
    "message": "로그인 성공! 토큰이 발행되었습니다.",
    "data": [
        {
            "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbHN0biIsImF1dGgiOiJDVVNUT01FUiIsImV4cCI6MTY0Njc0NjQwMH0.QxIN9OJ1TOkpyBvh0Ag7Bx3GZkb4fUG-dt0purTTSu0VUl5O47wUuEqwNURofObROs-s_foTps4Lhveu4sTmnQ",
            "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbHN0biIsImF1dGgiOiJDVVNUT01FUiIsImV4cCI6MTY0Njc3MTQ1Nn0.ysgITZQ4q8ClRhmxG1ZQx_ZdBlgh55oD-GkmKeU_kTHUc49uktJxvd9aaqjGW2xoNd2k9e5ze-JzqUh9P3vGOw"
        }
    ]
}
```


