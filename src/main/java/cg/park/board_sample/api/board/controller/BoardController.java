package cg.park.board_sample.api.board.controller;

import cg.park.board_sample.api.board.model.Board;
import cg.park.board_sample.api.board.service.BoardService;
import cg.park.board_sample.comm.util.HttpRequestHelper;
import cg.park.board_sample.comm.util.Message;
import cg.park.board_sample.comm.util.PagingUtil;
import cg.park.board_sample.comm.util.ResponseMav;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/board")
@Controller
public class BoardController {
//2023-01-27 releases

    @Autowired
    BoardService boardService;

    @GetMapping("")
    public ResponseMav list(Board board) {
        HttpServletRequest request = HttpRequestHelper.getCurrentRequest();

        int count = boardService.countBy(board);
        PagingUtil paging = new PagingUtil(request.getQueryString(), board.getPageNum(), count, board.getBlockCount(), board.getBlockPage(), request.getRequestURI());
        board.setStartCount(paging.getStartCount());
        board.setEndCount(paging.getEndCount());

        return new ResponseMav("board/board")
                .set("count", count)
                .set("list", boardService.findAll(board))
                .set("board", board)
                .set("paging", paging.getPagingHtml());
    }

    @GetMapping("/write")
    public ResponseMav write() {
        return new ResponseMav("/board/write");
    }

    @PostMapping("/write")
    public ResponseEntity<Message> writeSave(Board board) {
        return new ResponseEntity<>(boardService.save(board), HttpStatus.OK);
    }

    @GetMapping("/write/{boardNo}")
    public ResponseMav update(@PathVariable("boardNo") Integer boardNo) {
        return new ResponseMav("/board/write")
                .set("board", boardService.findByBoardNo(boardNo))
                .set("boardNo", boardNo);
    }

    // 업데이트 전체 바꿔야함
    @PostMapping("/write/{boardNo}")
    public ResponseEntity<Message> updateSave(@PathVariable("boardNo") Integer boardNo, Board board) {
        return new ResponseEntity<>(boardService.update(board.boardNo(boardNo)), HttpStatus.OK);
    }

    @GetMapping("/{boardNo}")
    public ResponseMav detail(@PathVariable("boardNo") Integer boardNo) {
        return new ResponseMav("/board/detail")
                .set("board", boardService.findByBoardNo(boardNo))
                .set("boardNo", boardNo);
    }

    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Message> delete(@PathVariable("boardNo") Integer boardNo) {
        return new ResponseEntity<>(boardService.delete(boardNo) , HttpStatus.OK);
    }

}
