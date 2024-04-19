package cg.park.board_sample.oauth.account.bean;

import lombok.Getter;
import lombok.Setter;

/*
 * API 키 객체 클래스
 */
@Getter
@Setter
public class ApiKeyBean {
    // API 키
    private String api;

    // API SECRET 키
    private String secret;

    // 콜백 URL
    private String callback;
}
