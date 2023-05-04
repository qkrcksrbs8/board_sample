package cg.park.board_sample.comm.util;

public enum Stamp {
    S001("S001", "정상 처리되었습니다."),
    F001("F001", "F001 입니다.");

    private final String code;
    private final String message;

    private Stamp(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
