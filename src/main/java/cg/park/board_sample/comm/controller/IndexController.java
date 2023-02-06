package cg.park.board_sample.comm.controller;

import cg.park.board_sample.comm.util.MemberSession;
import cg.park.board_sample.comm.util.ResponseMav;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String defaultPage() {
        return returnIndex();
    }

    @GetMapping("/index")
    public String index() {
        return returnIndex();
    }

    public String returnIndex() {
        return "/index";
    }

    @GetMapping("/head")
    public String head() {
        return "/include/head";
    }

    @GetMapping("/header")
    public ResponseMav header() {
        return new ResponseMav("/include/header")
                .set("member", MemberSession.getCurrentInstance().getMember());
    }

}
