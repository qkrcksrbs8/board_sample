package cg.park.board_sample.oauth.account.controller;

import cg.park.board_sample.comm.model.Member;
import cg.park.board_sample.comm.util.MemberSession;
import cg.park.board_sample.oauth.account.process.AccountGetProcess;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RequestMapping("/oauth")
@Controller
public class Callbck {


    @GetMapping("/callback")
    public String callback(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        String CLIENT_ID = "4f6a3f7fc682f73a86bc75ce8f003791";
        String REDIRECT_URI = "http://localhost:8080/oauth/callback";
        String strParam = "grant_type=authorization_code&client_id="+CLIENT_ID+"&redirect_uri="+REDIRECT_URI+"&code="+code;

        HashMap<String, String> map = new AccountGetProcess(request, response).send("https://kauth.kakao.com/oauth/token", strParam);

        if (null != map.get("nickname") && !"".equals(map.get("nickname"))) {
            Member member = new Member();
            member.setMemberId(map.get("nickname"));
            MemberSession.getCurrentInstance().login(member);
        }

        return "/auth/complete";
    }

}
