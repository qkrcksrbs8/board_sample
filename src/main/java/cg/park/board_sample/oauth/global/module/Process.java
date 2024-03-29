package cg.park.board_sample.oauth.global.module;

import cg.park.board_sample.oauth.account.module.AuthModule;
import cg.park.board_sample.oauth.account.module.KakaoAuthModule;
import cg.park.board_sample.oauth.account.module.NaverAuthModule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract public class Process
{
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    /**
     * 생성자 메서드
     *
     * @param request: [HttpServletRequest] HttpServletResponse 객체
     * @param response: [HttpServletResponse] HttpServletResponse 객체
     */
    protected Process(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
    }

    /**
     * 인증 모듈 반환 메서드
     *
     * @param platform: [String] 플랫폼
     *
     * @return [AuthModule] AuthModule 객체
     *
     * @throws NullPointerException 유효하지 않은 플랫폼
     */
    protected AuthModule getAuthModule(String platform) throws NullPointerException
    {
        if ("kakao".equals(platform)) return KakaoAuthModule.getInstance();
        if ("naver".equals(platform)) return NaverAuthModule.getInstance();
        throw new NullPointerException(Util.builder("'", platform, "' is invalid platform"));
    }
}