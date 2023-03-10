package cg.park.board_sample.comm.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ResponseMav extends ModelAndView {

    public ResponseMav() {}

    public ResponseMav(String viewName) {
        super.setViewName(viewName);
    }

    public ResponseMav(String viewName, HttpStatus status) {
        super.setViewName(viewName);
        super.setStatus(status);
    }

    public ResponseMav set(String key, Object obj) {
        super.addObject(key, obj);
        return this;
    }

}
