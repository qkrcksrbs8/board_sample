package cg.park.board_sample.comm.dao;

import cg.park.board_sample.comm.model.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthDao {

    Member findByMemberId(String memberId);
}
