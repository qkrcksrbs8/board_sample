package cg.park.board_sample.oauth.global.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBean<T>
{
    // 결과
    private boolean flag;

    // 제목
    private String title;

    // 메세지
    private String message;

    // 내용
    private T body;

    /**
     * 객체 문자열 반환 메서드
     *
     * @return [String] 객체 문자열
     */
    @Override
    public String toString()
    {
        String result;

        try
        {
            result = new ObjectMapper().writeValueAsString(this);
        }

        catch (JsonProcessingException e)
        {
            e.printStackTrace();

            result = null;
        }

        return result;
    }
}