package cg.park.board_sample.api.board.controller;

import cg.park.board_sample.api.board.model.Board;
import cg.park.board_sample.api.board.service.BoardService;
import cg.park.board_sample.comm.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/board")
@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("")
    public String list() {
        return "board/board";
    }

    @GetMapping("/write")
    public String write() {
        return "/board/write";
    }

    @PostMapping("/write")
    public ResponseEntity<Message> writeSave(Board board) {
        return new ResponseEntity<>(boardService.save(board), HttpStatus.OK);
    }

}
