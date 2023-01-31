package cg.park.board_sample.comm.service;

import cg.park.board_sample.comm.dao.AuthDao;
import cg.park.board_sample.comm.model.Member;
import cg.park.board_sample.comm.util.BoardUtil;
import cg.park.board_sample.comm.util.MemberSession;
import cg.park.board_sample.comm.util.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AuthDao authDao;

    public Message login(Member member) {
        if (isLoginParamFail(member))
            return new Message(false, "1");

        Member m = authDao.findByMemberId(member.getMemberId());
        if (null == m)
            return new Message(false, "2");

        if (!m.getPassword().equals(member.getPassword()))
            return new Message(false, "3");

        MemberSession.getCurrentInstance().login(member);

        return new Message(true);
    }

    public boolean isLoginParamFail(Member member) {
        return null == member || BoardUtil.isBlank(member.getMemberId()) || BoardUtil.isBlank(member.getPassword());
    }

    public void logout() {
        MemberSession.getCurrentInstance().logout();
    }

}
