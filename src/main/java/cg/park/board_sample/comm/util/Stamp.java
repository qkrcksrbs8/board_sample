package cg.park.board_sample.comm.util;

public enum Stamp {
    S001("S001", "정상 처리되었습니다."),
    F001("F001", "F로 시작하는 코드는 로직상 식별 가능한 오류"),
    P001("P001", "P로 시작하는 코드는 파라미터 관련."),
    E001("E001", "E로 시작하는 코드는 오류.")
    ;

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
