package cg.park.board_sample.comm.controller;

import cg.park.board_sample.comm.util.ResponseMav;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@Controller
public class AuthController {

    @GetMapping("/login")
    public ResponseMav login() {
        return new ResponseMav("auth/login");
    }
}
