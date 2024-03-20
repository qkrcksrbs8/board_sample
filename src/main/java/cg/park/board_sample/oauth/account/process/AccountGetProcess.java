package cg.park.board_sample.oauth.account.process;

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
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.UUID;
import cg.park.board_sample.oauth.global.module.Process;
import org.springframework.http.ResponseEntity;
import sun.net.www.protocol.http.HttpURLConnection;

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

    public void send(String reqUrl, String code) {
        String CLIENT_ID = "4f6a3f7fc682f73a86bc75ce8f003791";
        String REDIRECT_URI = "http://localhost:8080/oauth/callback";
        //결과값 담을 변수
        String returnStr 	   = "";

        HttpsURLConnection con = null;

        try {

            URL url = new URL(reqUrl);

            StringBuffer buf = new StringBuffer();

            con = (HttpsURLConnection)url.openConnection();

            //http method 설정
            con.setRequestMethod("POST");

            //서버통신 timeout 설정 (30초)
            con.setConnectTimeout(30000);

            //스트림읽기 timeout 설정 (30초)
            con.setReadTimeout(30000);

            //헤더설정
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Authorization", "sdfsdfsdfsdf");

            //OutputStream으로 POST 데이터 전달 옵션
            con.setDoOutput(true);

            //연결
            con.connect();

            // 송신할 데이터 전송.
            DataOutputStream dos = new DataOutputStream(con.getOutputStream());
            //        String strParam = "param1=apple&param2=banana&param3=grape";
            String strParam = "grant_type=authorization_code&client_id="+CLIENT_ID+"&redirect_uri="+REDIRECT_URI+"&code="+code;
            dos.writeBytes(strParam);// post body data
            dos.flush();
            dos.close();

            //응답 읽기
            StringBuilder response = new StringBuilder();
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
            HashMap<String, String> map = new HashMap<>();
            while (t.hasMoreTokens()) {
                String[] arr = t.nextToken().split(":");
                map.put(arr[0], arr[1]);
            }
            System.out.println("kakao result: "+returnStr);
            System.out.println("kakao map: "+map);
        }
        catch (Exception e) {
            System.out.println("ERROR: "+e);
        }
    }

}
