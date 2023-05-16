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

    public static void build(String uri, int probability) {
        boolean duple = isDuple(uri);
        session().setAttribute("DUPLE_URI_YN", duple ? "Y" : "N");
        if (duple) {
            session().setAttribute("IS_STAMP", "N");
            return;
        }
        session().setAttribute("LAST_PATH", uri);

        StringBuffer hwaniBearCode = new StringBuffer();

        // 화니베어 코드 돌리기
        boolean isHwaniBear = probability > new Random().nextInt(100);

        // 화니베어 노출 확정 시 화니베어 코드 추출
        if (isHwaniBear)
            hwaniBearCode.append("STAMP00"+new Random().nextInt(6));

        session().setAttribute("IS_STAMP", isHwaniBear ? "Y" : "N");
        session().setAttribute("STAMP_CODE", hwaniBearCode.toString());
    }

    public static void destroy() {
        session().removeAttribute("DUPLE_URI_YN");
        session().removeAttribute("LAST_PATH");
        session().removeAttribute("STAMP_CODE");
        session().setAttribute("IS_STAMP", "N");
    }

    public static boolean isBlock(String uri) {
        if (uri.contains("/index") && !uri.contains("/other/")) return true;
        if (uri.contains("/introduce/")) return true;
        if (uri.contains("/media/")) return true;
        if (uri.contains("/share/promotion/list")) return true;
        if (uri.contains("/share/promotion/view")) return true;
        if (uri.contains("/enjoy/")) return true;
        if (uri.contains("/buy/")) return true;
        return false;
    }

    public static boolean isNone(String uri) {
        return (uri.contains("/other/") || uri.contains("/include/")) ? true : false;
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

    public static void init() {
        String uri = replaceJsp(uri());

        if (isBlank(uri))
            return;

        if (isBlock(uri)) {
            build(uri, 30);
            return;
        }

        if (isNone(uri)) {
            destroy();
            return;
        }
    }

    public static boolean isBlank(String s) {
        return null == s || "".equals(s.trim());
    }

    public static String replaceJsp (String str) {
        return null == str
                ? ""
                : str.replace("WEB-INF/views/pc", "kor")
                .replace("WEB-INF/views/mo", "kor")
                .replace(".jsp", "");
    }

}
