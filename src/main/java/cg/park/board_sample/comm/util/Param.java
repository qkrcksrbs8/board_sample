package cg.park.board_sample.comm.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Param extends HashMap {

    public Param () {}

    public Param (Object obj) {
        super.put("data", obj);
    }
    Param (String key, String value) {
        super.put(key, value);
    }
    public Param set(String key, String value) {
        super.put(key, value);
        return this;
    }

    public Param set(String key, int value) {
        super.put(key, value);
        return this;
    }
}
