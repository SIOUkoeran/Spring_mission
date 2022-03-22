# Basic 

1. 회원가입, 로그인 후 jwt를 통해 api 통신 가능.
2. 정상적인 token이 아닐 시 `filter`를 통해서 오류 반환
3. ShopOwner가 아닌 경우 Shop관련 api 중 Get 메소드를 제외하고서 접근 불가능 
4. `usernameNotfoundException` 발생 후 handler 통해서 반환
5. `AreaEntity`는 `AreaConstant.java` 와 `AreaMap.java` 에서 랜덤으로 하나를 뽑아 지정.
6. Dto valid 작업 후, 오류 발생 시 해당 페이지로 다시 리턴, 오류메세지 페이지에 출력
7. 오류 발생시 기본적으로 200을 반환하지만, body에서는 `기존 오류 코드 * 10` 값 반환

8. 그 외에도 세부사항 따름.


### 5번
```java
public class AreaMap {
    public static final HashMap<Integer, AreaConstant> map = new HashMap<>();

    static {
        map.put(1,AreaConstant.DUMMY1);
        map.put(2,AreaConstant.DUMMY2);
        map.put(3,AreaConstant.DUMMY3);
    }
}

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
```

# 화면 예시

![image](https://user-images.githubusercontent.com/35398494/159499294-97d3eaf7-408e-4c18-a90e-89e9de58f0ce.png)

![image](https://user-images.githubusercontent.com/35398494/159499572-1f435d09-5a8b-4deb-81c2-6f9436b48259.png)

![image](https://user-images.githubusercontent.com/35398494/159499612-ed696887-3e4a-46fa-b87b-5f22f81bd981.png)

![image](https://user-images.githubusercontent.com/35398494/159499884-eb63a5d4-1e21-4ea9-82f5-98198454e8ae.png)
