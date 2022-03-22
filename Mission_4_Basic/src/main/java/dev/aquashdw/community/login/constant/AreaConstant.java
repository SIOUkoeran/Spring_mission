package dev.aquashdw.community.login.constant;


import lombok.Getter;

@Getter
public enum AreaConstant {

    DUMMY1("서울시", "서초구", "서초동", 37.4877, 127.0174),
    DUMMY2("서울시", "강남구","역삼동", 37.4999,127.0374),
    DUMMY3("서울시", "강남구", "삼성동",37.5140,127.0565);

    private final String major;
    private final String minor;
    private final String path;
    private final double longitude;
    private final double latitude;

    AreaConstant(String major, String minor, String path, double longitude, double latitude) {
        this.major = major;
        this.minor = minor;
        this.path = path;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
