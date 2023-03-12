package cg.park.board_sample.comm.controller;

import cg.park.board_sample.comm.model.Member;
import cg.park.board_sample.comm.service.AuthService;
import cg.park.board_sample.comm.util.Message;
import cg.park.board_sample.comm.util.ResponseMav;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@Controller
public class AuthController {

    @Autowired
    AuthService authService;

    @GetMapping("/login")
    public ResponseMav login() {
        return new ResponseMav("auth/login");
//        return new ResponseMav("auth/login", 302);
    }

    @PostMapping("/login")
    public ResponseEntity<Message> login(Member member) {
        return new ResponseEntity<>(authService.login(member), HttpStatus.OK);
    }

    @DeleteMapping("/login")
    public ResponseMav logout() {
        authService.logout();
        return new ResponseMav("redirect:/");
    }

    @GetMapping("/logon")
    public ResponseMav logon() {
        return new ResponseMav("auth/logon");
    }

}
