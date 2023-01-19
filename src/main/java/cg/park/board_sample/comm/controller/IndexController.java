package cg.park.board_sample.comm.controller;

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
    public String header() {
        return "/include/header";
    }
}
