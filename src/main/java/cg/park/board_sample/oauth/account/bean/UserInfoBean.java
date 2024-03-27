package cg.park.board_sample.oauth.account.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
 * 사용자 정보 객체 함수
 */
@Getter
@Setter
@AllArgsConstructor
public class UserInfoBean {
    // 이메일
    private String email;

    // 이름
    private String name;

    // 프로필 이미지 URL
    private String profile;

    // 플랫폼
    private String platform;
}
