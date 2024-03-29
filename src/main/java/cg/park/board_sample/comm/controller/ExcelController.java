package cg.park.board_sample.comm.controller;

import cg.park.board_sample.comm.service.ExcelService;
import cg.park.board_sample.comm.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RequestMapping("/excel")
@RestController
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @PostMapping("/extension/{field}")
    @ResponseBody
    public Message extensionCheck(MultipartHttpServletRequest request, @PathVariable("field") String field) {
        return excelService.extensionCheck(request, field);
    }

}
