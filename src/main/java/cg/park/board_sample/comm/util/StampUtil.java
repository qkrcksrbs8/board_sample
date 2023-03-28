package cg.park.board_sample.comm.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Component
public class StampUtil {

    public static String uri() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRequestURI();
    }

    private static HttpSession session() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
    }

    private static String lastUri() {
        return session().getAttribute("LAST_PATH") == null ? "" : session().getAttribute("LAST_PATH").toString();
    }

    private static boolean isDuple(String url) {
        return lastUri().equals(url);
    }

    public static void init(String uri, int probability) {
        boolean duple = isDuple(uri);
        session().setAttribute("DUPLE_URI_YN", duple ? "Y" : "N");
        if (duple) {
            session().setAttribute("IS_STAMP", "N");
            return;
        }
        session().setAttribute("LAST_PATH", uri);

        StringBuffer hwaniBearCde = new StringBuffer();

        // 화니베어 코드 돌리기
        boolean isHwaniBear = probability > new Random().nextInt(100);

        // 화니베어 노출 확정 시 화니베어 코드 추출
        if (isHwaniBear)
            hwaniBearCde.append("STAMP00"+new Random().nextInt(6));

        session().setAttribute("IS_STAMP", isHwaniBear ? "Y" : "N");
        session().setAttribute("STAMP_CODE", hwaniBearCde.toString());
    }

    public static void destroy() {
        session().removeAttribute("DUPLE_URI_YN");
        session().removeAttribute("LAST_PATH");
        session().removeAttribute("STAMP_CODE");
        session().setAttribute("IS_STAMP", "N");
    }

    public static boolean isBlock(String uri) {
        if (-1 < uri.indexOf("/index") && -1 == uri.indexOf("/other/")) return true;
        if (-1 < uri.indexOf("/introduce/")) return true;
        if (-1 < uri.indexOf("/media/")) return true;
        if (-1 < uri.indexOf("/share/promotion/list")) return true;
        if (-1 < uri.indexOf("/share/promotion/view")) return true;
        if (-1 < uri.indexOf("/enjoy/")) return true;
        if (-1 < uri.indexOf("/buy/")) return true;
        return false;
    }

    public static boolean isNone(String uri) {
        if (-1 < uri.indexOf("/other/")) return true;
        if (-1 < uri.indexOf("/include/")) return true;
        return false;
    }

    public static String currentStampStatus() {
        return (null == session().getAttribute("IS_STAMP"))
                ? "N"
                : session().getAttribute("IS_STAMP").toString();
    }

    public static String currentStampCode() {
        return (null == session().getAttribute("STAMP_CODE"))
                ? ""
                : session().getAttribute("STAMP_CODE").toString();
    }
}
