package cg.park.board_sample.comm.aop;

import cg.park.board_sample.comm.util.BoardUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class ControllerLogAop {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* cg.park.board_sample.api..controller.*.*(..))")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String param = BoardUtil.mapToStr(request.getParameterMap());
        logger.info("SSID = {}, ===================START===================", BoardUtil.requestedSessionId());
        logger.info("SSID = {}, @Before : {}, Method : {}, URI : {}, param : {}", BoardUtil.requestedSessionId(), BoardUtil.currentType(joinPoint), request.getMethod(), request.getRequestURI(), param);
    }

    @AfterReturning(pointcut = "execution(* cg.park.board_sample.api..controller.*.*(..))", returning="retValue")
    public void after(JoinPoint joinPoint, Object retValue) {
        logger.info("SSID = {}, @After : {}, result : {}", BoardUtil.requestedSessionId(), BoardUtil.currentType(joinPoint), retValue);
        logger.info("SSID = {}, ===================E N D===================", BoardUtil.requestedSessionId());
    }

    @AfterThrowing(pointcut = "execution(* cg.park.board_sample.api..controller.*.*(..))", throwing = "ex")
    public void afterThrowingAnException(JoinPoint joinPoint, Exception ex) {
        logger.info("SSID = {}", BoardUtil.requestedSessionId());
        logger.info("SSID = {}, ===================E N D===================", BoardUtil.requestedSessionId());
    }

}
