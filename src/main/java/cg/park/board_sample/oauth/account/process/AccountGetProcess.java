package cg.park.board_sample.oauth.account.process;

import cg.park.board_sample.comm.util.Param;
import cg.park.board_sample.oauth.account.module.AuthModule;
import cg.park.board_sample.oauth.global.bean.ResponseBean;
import cg.park.board_sample.oauth.global.module.Util;
import org.springframework.http.HttpStatus;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.UUID;
import cg.park.board_sample.oauth.global.module.Process;
import org.springframework.http.ResponseEntity;

public class AccountGetProcess extends Process
{
    /**
     * 생성자 메서드
     *
     * @param request: [HttpServletRequest] HttpServletRequest 객체
     * @param response: [HttpServletResponse] HttpServletResponse 객체
     */
    public AccountGetProcess(HttpServletRequest request, HttpServletResponse response)
    {
        super(request, response);
    }

    /**
     * 인증 URL 응답 반환 메서드
     *
     * @param platform: [String] 플랫폼
     *
     * @return [Response] 응답 객체
     */
    public ResponseEntity getAuthorizationUrlResponse(HttpServletRequest request, String platform)
    {
        ResponseEntity response;

        ResponseBean<String> responseBean = new ResponseBean<>();

        // 인증 URL 응답 생성 시도
        try
        {
            String state = UUID.randomUUID().toString();

            request.getSession().setAttribute("state", state);

            AuthModule authModule = getAuthModule(platform);

            responseBean.setFlag(true);
            responseBean.setTitle("success");
            responseBean.setMessage(Util.builder(platform, " authrorization url response success"));
            responseBean.setBody(authModule.getAuthorizationUrl(state));

            response = new ResponseEntity<>(responseBean, HttpStatus.OK);
        }

        // 예외
        catch (Exception e)
        {
            e.printStackTrace();

            responseBean.setFlag(false);
            responseBean.setTitle(e.getClass().getSimpleName());
            responseBean.setMessage(e.getMessage());
            responseBean.setBody(null);

            response = new ResponseEntity<>(responseBean, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    /**
     * 사용자 정보 응답 반환 메서드
     *
     * @param accessCookie: [String] 접근 토큰 쿠키
     *
     * @return [Response] 응답 객체
     */
//    public Response getUserInfoResponse(String accessCookie)
//    {
//        Response response;
//
//        ResponseBean<UserInfoBean> responseBean = new ResponseBean<>();
//
//        // 사용자 정보 응답 생성 시도
//        try
//        {
//            Jws<Claims> jws = JwtModule.openJwt(accessCookie);
//
//            String accessToken = jws.getBody().get("access", String.class);
//            String platform = jws.getBody().get("platform", String.class);
//
//            AuthModule authModule = getAuthModule(platform);
//
//            com.github.scribejava.core.model.Response userInfoResponse = authModule.getUserInfo(accessToken);
//
//            // 응답이 정상적이지 않을 경우
//            if (userInfoResponse.getCode() != 200)
//            {
//                throw new OAuthResponseException(userInfoResponse);
//            }
//
//            responseBean.setFlag(true);
//            responseBean.setTitle("success");
//            responseBean.setMessage("user info response success");
//            responseBean.setBody(authModule.getUserInfoBean(userInfoResponse.getBody()));
//
//            response = Response.ok(responseBean, MediaType.APPLICATION_JSON).build();
//        }
//
//        // 예외
//        catch (Exception e)
//        {
//            e.printStackTrace();
//
//            responseBean.setFlag(false);
//            responseBean.setTitle(e.getClass().getSimpleName());
//            responseBean.setMessage(e.getMessage());
//            responseBean.setBody(null);
//
//            response = Response.status(Response.Status.BAD_REQUEST).entity(responseBean).type(MediaType.APPLICATION_JSON).build();
//        }
//
//        return response;
//    }

    public HashMap<String, String> send(String reqUrl, String strParam) {
        //결과값 담을 변수
        String returnStr 	   = "";
        HttpsURLConnection con = null;
        HashMap<String, String> map = new HashMap<>();

        try {
            URL url = new URL(reqUrl);
            StringBuffer buf = new StringBuffer();
            con = (HttpsURLConnection)url.openConnection();

            con.setRequestMethod("POST");
            con.setConnectTimeout(30000);//서버통신 timeout 설정 (30초)
            con.setReadTimeout(30000);//스트림읽기 timeout 설정 (30초)

            //헤더설정
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Authorization", "sdfsdfsdfsdf");

            con.setDoOutput(true);//OutputStream으로 POST 데이터 전달 옵션
            con.connect();//연결

            DataOutputStream dos = new DataOutputStream(con.getOutputStream());// 송신할 데이터 전송.
            dos.writeBytes(strParam);// post body data
            dos.flush();
            dos.close();

            StringBuilder response = new StringBuilder(); //응답 읽기
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();
            returnStr = response.toString();
            returnStr = returnStr.replaceAll("\"", "");
            returnStr = returnStr.replaceAll("\\{", "");
            returnStr = returnStr.replaceAll("}", "");
            StringTokenizer t = new StringTokenizer(returnStr, ",");
            while (t.hasMoreTokens()) {
                String[] arr = t.nextToken().split(":");
                map.put(arr[0], arr[1]);
            }
            System.out.println("kakao result: "+returnStr);
            System.out.println("kakao map: "+map);

            return send("https://kapi.kakao.com/v2/user/me?property_keysproperty_keys=kakao_account.profile", new Param("token", map.get("access_token")));
        }
        catch (Exception e) {
            System.out.println("ERROR: "+e);
            map.put("RESULT_MSG", e.getMessage());
            map.put("RESULT_CODE", "E001");
            return map;
        }
    }

    public HashMap<String, String> send(String reqUrl, Param param) {

        HashMap<String, String> map = new HashMap<>();
        try {
            // GET 요청을 보낼 URL
            URL url = new URL(reqUrl);

            // HttpURLConnection 객체 생성
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();// https

            // 요청 방식 설정 (GET 요청은 기본적으로 설정되어 있음)
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer "+param.get("token"));
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            // 응답 코드 확인
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // 응답 내용 읽기

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            String returnStr 	   = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();


            returnStr = response.toString();
            System.out.println("kakao /user/me returnStr: "+returnStr);
            returnStr = returnStr.replaceAll("\"", "");
            returnStr = returnStr.replaceAll("\\{", "");
            returnStr = returnStr.replaceAll("}", "");
            StringTokenizer t = new StringTokenizer(returnStr, ",");
            while (t.hasMoreTokens()) {
                String[] arr = t.nextToken().split(":");
                map.put(arr[0], arr[1]);
            }



            // 응답 내용 출력
            System.out.println("kakao /user/me returnStr: "+returnStr);
            System.out.println("kakao /user/me map: "+map);
            String nickname = returnStr.substring(returnStr.indexOf("nickname"), returnStr.indexOf("nickname")+returnStr.substring(returnStr.indexOf("nickname")).indexOf(","));
            System.out.println("nickname: "+nickname);

            // 연결 종료
            connection.disconnect();
            map.put("nickname", nickname.replace("nickname:", ""));
        } catch (Exception e) {
            map.put("nickname: ", "");
            e.printStackTrace();
        }
        return map;
    }

}
