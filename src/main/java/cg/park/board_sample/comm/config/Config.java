package cg.park.board_sample.comm.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class Config {
    private static Properties prop = null;
    private static File file = null;
    private static long lastModified = -1L;

    public Config() {
    }

    private static void reloadConf() throws Exception {
        prop = new Properties();
        ClassLoader cl = Config.class.getClassLoader();
        URL url = cl.getResource("conf.properties");
        file = new File(url.getFile());
        lastModified = file.lastModified();
        InputStream in = new FileInputStream(file);
        prop.load(in);
        in.close();
    }

    private static boolean isModified() throws Exception {
        boolean b = false;
        if (lastModified != -1L && file != null) {
            if (lastModified != file.lastModified()) {
                lastModified = file.lastModified();
                b = true;
            }
        } else {
            b = true;
        }

        return b;
    }

    private static String getString(String key, String defaultValue) {
        try {
            if (prop == null || isModified()) {
                reloadConf();
            }

            return prop.getProperty(key, defaultValue).trim();
        } catch (Exception var3) {
            return "";
        }
    }

    public static String get(String key, String defaultValue) {
        try {
            return getString(key, defaultValue);
        } catch (Exception var3) {
            return "";
        }
    }

    public static String get(String key) {
        try {
            return getString(key, "");
        } catch (Exception var2) {
            return "";
        }
    }
}
