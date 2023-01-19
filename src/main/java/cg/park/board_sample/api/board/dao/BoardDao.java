package cg.park.board_sample.api.board.dao;

import cg.park.board_sample.api.board.model.Board;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDao {

    public void save(Board board);
}
