package cg.park.board_sample.comm.util;

import lombok.Data;

@Data
public class Message {

    private boolean isSucceed;
    private String message;
    private Param param;	//파라미터 처리시에 사용.

    public Message(boolean isSucceed) {
        this.isSucceed = isSucceed;
        this.message = (isSucceed ? "정상 처리되었습니다." : "오류가 발생했습니다.");
        this.param = new Param();
    }

    public Message(boolean isSucceed, String message) {
        this.isSucceed = isSucceed;
        this.message = message;
        this.param = new Param();
    }

    public Message(boolean isSucceed, String message, Param param) {
        this.isSucceed = isSucceed;
        this.message = message;
        this.param = param;
    }

    public boolean getStatus() {
        return this.isSucceed;
    }
}
