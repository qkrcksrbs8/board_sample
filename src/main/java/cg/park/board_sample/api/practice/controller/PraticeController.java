package cg.park.board_sample.api.practice.controller;

import cg.park.board_sample.comm.util.ResponseMav;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/practice")
@Controller
public class PraticeController {

    @GetMapping("")
    public ResponseMav praticeIndex() {
        return new ResponseMav("/practice/play");
    }

}
