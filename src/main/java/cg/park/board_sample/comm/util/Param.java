package cg.park.board_sample.comm.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Param extends HashMap {

    public Param () {}

    public Param (Object obj) {
        super.put("data", obj);
    }
    public Param (String key, String value) {
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

    public Param set(String key, Long value) {
        super.put(key, value);
        return this;
    }

    public Param set(String key, Double value) {
        super.put(key, value);
        return this;
    }

}
