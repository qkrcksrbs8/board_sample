package cg.park.board_sample.comm.controller;

import cg.park.board_sample.comm.util.HttpRequestHelper;
import cg.park.board_sample.comm.util.MemberSession;
import cg.park.board_sample.comm.util.ResponseMav;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

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
                .set("member", MemberSession.getCurrentInstance().getMember())
                .set("isHwaniBear", HttpRequestHelper.getCurrentSession().getAttribute("IS_HWANI_BEAR"))
                .set("hwaniBearCode", HttpRequestHelper.getCurrentSession().getAttribute("HWANI_BEAR_CODE"));
    }

}
