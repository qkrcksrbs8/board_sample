package cg.park.board_sample.api.board.dao;

import cg.park.board_sample.api.board.model.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {

    public int countBy(Board board);
    public List<Board> findAll(Board board);
    public void save(Board board);
    public Board findByBoardNo(Integer boardNo);

    public void update(Board board);

    public void delete(Integer boardNo);

}
