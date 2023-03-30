package cg.park.board_sample.comm.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpRequestHelper {

    public static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public static HttpSession getCurrentSession() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
    }

}
