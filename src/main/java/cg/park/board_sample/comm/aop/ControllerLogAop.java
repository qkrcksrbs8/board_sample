package cg.park.board_sample.comm.aop;

import cg.park.board_sample.comm.util.BoardUtil;
import cg.park.board_sample.comm.util.HttpRequestHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
@Aspect
public class ControllerLogAop {

    Logger logger = LoggerFactory.getLogger(super.getClass());

    @Before(
            "execution(* cg.park.board_sample.api..controller.*.*(..)) " +
            "|| execution(* cg.park.board_sample.comm..controller.ExcelController.*(..))"
    )
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = HttpRequestHelper.getCurrentRequest();
        String param = BoardUtil.mapToStr(request.getParameterMap());
        String uri = request.getMethod() + "["+request.getRequestURI()+"]";

        HttpSession session = request.getSession();
        String lastUri = session.getAttribute("LAST_URI") == null ? "" : session.getAttribute("LAST_URI").toString();
        session.setAttribute("DUPLE_URI_YN", (lastUri.equals(request.getRequestURI()))? "Y" : "N");
        session.setAttribute("LAST_URI", request.getRequestURI());

        logger.info("SSID = {}, ===================START===================", BoardUtil.requestedSessionId());
        logger.info("SSID = {}, @Before : {}, {}, param : {}", BoardUtil.requestedSessionId(), BoardUtil.currentType(joinPoint), uri, param);
    }

    @AfterReturning(
            pointcut =
                "execution(* cg.park.board_sample.api..controller.*.*(..)) " +
                "|| execution(* cg.park.board_sample.comm..controller.ExcelController.*(..))"
            , returning="retValue"
    )
    public void after(JoinPoint joinPoint, Object retValue) {
        logger.info("SSID = {}, @After : {}, result : {}", BoardUtil.requestedSessionId(), BoardUtil.currentType(joinPoint), retValue);
        logger.info("SSID = {}, ===================E N D===================", BoardUtil.requestedSessionId());
    }

    @AfterThrowing(
            pointcut =
                "execution(* cg.park.board_sample.api..controller.*.*(..)) " +
                "|| execution(* cg.park.board_sample.comm..controller.ExcelController.*(..))"
            , throwing = "ex"
    )
    public void afterThrowingAnException(JoinPoint joinPoint, Exception ex) {
        logger.info("SSID = {}", BoardUtil.requestedSessionId());
        logger.info("SSID = {}, ===================E N D===================", BoardUtil.requestedSessionId());
    }

}
