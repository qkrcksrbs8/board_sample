package cg.park.board_sample.comm.util;

import org.springframework.util.StringUtils;

public final class BoardUtil {

    public static boolean isBlank(final CharSequence cs) {
        final int strLen = cs.length();
        if (strLen == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
