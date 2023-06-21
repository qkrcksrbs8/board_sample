package cg.park.board_sample.comm.config;

import org.springframework.stereotype.Component;
import sun.plugin.dom.exception.InvalidAccessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ipAccess {

    public String[] ips(String ip) {
        return new String[] {ip, ip2(ip), ip3(ip)};
    }

    public String ip2(String ip) {
        return ip.split("[.]")[0] + "." + ip.split("[.]")[1] + "." + ip.split("[.]")[2] + "." + "*";
    }

    public String ip3(String ip) {
        return ip.split("[.]")[0] + "." + ip.split("[.]")[1] + "." + "*" + "." + "*";
    }


    public boolean isCheckIp(String ip, String ips[]) {
        return (ip.equals(ips[0]) || ip.equals(ips[1]) || ip.equals(ips[2]));
    }

    private boolean isAllowedIp(String ip) {

        if ("0:0:0:0:0:0:0:1".equals(ip))
            return true;

        if ("".equals(Config.get("allow.iplist")))
            return false;

        String[] ips = ips(ip);

        for (String _ip :Config.get("allow.iplist").split(","))
            if (isCheckIp(_ip.trim(), ips))
                return true;

        return false;
    }

    private static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip != null && ip.indexOf(",") > -1) {
            ip = ip.substring(0, ip.indexOf(","));
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
