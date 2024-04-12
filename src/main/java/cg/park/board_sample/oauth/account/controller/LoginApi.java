package cg.park.board_sample.oauth.account.controller;

import cg.park.board_sample.comm.util.Param;
import cg.park.board_sample.oauth.account.process.AccountGetProcess;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RequestMapping("/oauth/login")
@Controller
public class LoginApi {

    @GetMapping("/{platform}")
    public ResponseEntity<Param> authorizationUrlResponse(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("platform") String platform) {
        return new AccountGetProcess(request, response).getAuthorizationUrlResponse(request, platform);
    }

}
