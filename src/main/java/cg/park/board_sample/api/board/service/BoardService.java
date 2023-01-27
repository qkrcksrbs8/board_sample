package cg.park.board_sample.api.board.service;

import cg.park.board_sample.api.board.dao.BoardDao;
import cg.park.board_sample.api.board.model.Board;
import cg.park.board_sample.comm.util.BoardUtil;
import cg.park.board_sample.comm.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardDao boardDao;

    public int countBy(Board board) {
        return boardDao.countBy(board);
    }

    public List<Board> findAll(Board board) {
        return boardDao.findAll(board);
    }

    public Message save(Board board) {
        Message check = saveCheck(board);
        if (!check.getStatus())
            return check;

        return save(board, true);
    }

    private Message saveCheck(Board board) {
        if (BoardUtil.isBlank(board.getTitle()))
            return new Message(false, "타이틀을 입력해주세요.");

        if (BoardUtil.isBlank(board.getDetail()))
            return new Message(false, "내용을 입력해주세요.");

        return new Message(true);
    }

    private Message save(Board board, Boolean status) {
        if (!status)
            return new Message(false);

        boardDao.save(board);
        return new Message(true);
    }

    public Board findByBoardNo(Integer boardNo) {
        return boardDao.findByBoardNo(boardNo);
    }

    private Message update(Board board, Boolean status) {
        if (!status)
            return new Message(false);

        boardDao.update(board);
        return new Message(true);
    }

    public Message update(Board board) {
        if (0 == board.getBoardNo())
            return new Message(false);

        Message check = saveCheck(board);
        if (!check.getStatus())
            return check;

        return update(board, true);
    }

    public Message delete(Integer boardNo) {
        if (0 == boardNo)
            return new Message(false);

        boardDao.delete(boardNo);
        return new Message(true);
    }

}
