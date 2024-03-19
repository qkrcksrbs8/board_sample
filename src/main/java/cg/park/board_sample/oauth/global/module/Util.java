package cg.park.board_sample.oauth.global.module;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

public class Util
{
    /**
     * 텍스트 빌더 결과 반환 메서드
     *
     * @param objs: [Object[]] 객체 가변 인수
     *
     * @return [String] 빌더 결과 문자열
     */
    public static String builder(Object... objs)
    {
        StringBuilder builder = new StringBuilder();

        for (Object obj : objs)
        {
            builder.append(obj);
        }

        return builder.toString();
    }

    /**
     * WEB-INF 경로 반환 메서드
     *
     * @return [String] WEB-INF 경로
     */
    public static String getInfPath()
    {
        return Util.class.getResource("/").getPath();
    }

    /**
     * 프로퍼티 맵 반환 메서드
     *
     * @param name: [String] 프로퍼티 파일명
     *
     * @return [HashMap<String, String>] 프로퍼티 맵
     *
     * @throws IOException 파일 입출력 예외
     */
    public static HashMap<String, String> getProperties(String name) throws IOException
    {
        String path = builder(getInfPath(), File.separator, name);
        // 파일명 .properties로 끝나지 않을 경우
        if (!name.endsWith(".properties"))
        {
            path = builder(path, ".properties");
        }

        HashMap<String, String> map = new HashMap<>();

        Properties properties = new Properties();
        properties.load(new FileInputStream(path));
        properties.keySet().forEach(e -> {
            String key = e.toString();

            map.put(key, properties.getProperty(key));
        });

        return map;
    }
}