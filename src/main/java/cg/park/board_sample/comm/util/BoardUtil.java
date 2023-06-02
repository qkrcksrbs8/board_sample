package cg.park.board_sample.comm.util;

import org.aspectj.lang.JoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Map;

public final class BoardUtil {

    public static boolean isBlank(final CharSequence cs) {
        if (cs == null)
            return true;

        final int strLen = cs.length();
        if (strLen == 0)
            return true;

        for (int i = 0; i < strLen; i++)
            if (!Character.isWhitespace(cs.charAt(i)))
                return false;

        return true;
    }

    public static String convertStr(String str) {
        return (null == str) ? "" : str;
    }

    public static String requestedSessionId() {
        return convertStr((((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()).getRequestedSessionId());
    }

    public static String mapToStr(Map<String, String[]> map){
        return (map == null || map.keySet().size() == 0)? "{null}":appendMapToStr(map);
    }

    public static String appendMapToStr(Map<String, String[]> map){
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        for (String key : map.keySet())
            sb.append(strType(map.get(key), key)).append(", ");

        return (sb.length() > 2000)
                ? sb.substring(0, 2000)
                : sb.append("}").toString().replace(", }", "}");
    }

    public static String strType (Object val, String key) {
        boolean chkKey = false;
        if (val == null) return key + "null";
        if (val instanceof String) return keyVal(key, val, chkKey);
        if (val instanceof String[]){
            String[] arr = (String[]) val;
            if(arr.length == 1) return keyVal(key, arr[0], chkKey);
            StringBuilder sb = new StringBuilder();
            Arrays.stream(arr).forEach(a -> sb.append(keyVal(key, a, chkKey)));
        }
        return keyVal(key, val, chkKey);
    }

    public static String keyVal(String key, Object obj, boolean chkKey) {
        return (chkKey)? key+":"+obj.toString().replaceAll(".*", "****") : key+":"+obj;
    }

    public static String currentType(JoinPoint joinPoint) {
        return joinPoint.getSignature().toShortString();
    }

//    SELECT s.business_number, IFNULL(i.count,0)
//    FROM stores s
//    LFET OUTER JOIN (SELECT COUNT(*) count, business_number
//    FROM interest_store
//    GROUP BY business_number) i
//    ON s.business_number = i.business_number
}
