package cg.park.board_sample.api.board.controller;

import cg.park.board_sample.api.board.model.Board;
import cg.park.board_sample.api.board.service.BoardService;
import cg.park.board_sample.comm.util.Message;
import cg.park.board_sample.comm.util.ResponseMav;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board")
@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("")
    public ResponseMav list(Board board) {
        return new ResponseMav("board/board")
                .set("count", boardService.countBy(board))
                .set("list", boardService.findAll(board));
    }

    @GetMapping("/write")
    public ResponseMav write() {
        return new ResponseMav("/board/write");
    }

    @PostMapping("/write")
    public ResponseEntity<Message> writeSave(Board board) {
        return new ResponseEntity<>(boardService.save(board), HttpStatus.OK);
    }

    @GetMapping("/board/{boardNo}")
    public ResponseMav detail(@PathVariable("boardNo") Integer boardNo) {
        return new ResponseMav("/board/detail")
                .set("board", boardService.findByBoardNo(boardNo));
    }

}
