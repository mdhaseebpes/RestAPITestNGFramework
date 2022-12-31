package spotify.oauth2.token;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static spotify.oauth2.api.Route.API;
import static spotify.oauth2.api.Route.TOKEN;
import static spotify.oauth2.api.SpecBuilder.getResponseSpec;

public class AccessToken {
    private static String access_token;
    private static Instant expiry_time;

    public synchronized static String getToken(){
        try {
            if(access_token==null || Instant.now().isAfter(expiry_time)){
                System.out.println("Renewing the token....");
               Response response = renewAccessToken();
               access_token =response.path("access_token");
               int expiryDurationInSecs = response.path("expires_in");
               expiry_time = Instant.now().plusSeconds(expiryDurationInSecs-400);
            }else{
                System.out.println("Token is good to use...");
            }

        }catch (Exception e){
             e.printStackTrace();
              throw new RuntimeException("ABORT !!! Failed to get token");
        }
        return access_token;
    }

    public static Response renewAccessToken(){
        HashMap<String,String> formParams = new HashMap<String, String>();
        formParams.put("grant_type" ,"refresh_token");
        formParams.put("refresh_token","AQC_8C8wI06F4A-3wTNx4lIruj-76nEipZ1-x5_Y6OxbJCQnM1VRQ8gkNk3Mb10O9cJ7sbphc2dgXEzS0L9i39VBbqa7lmDhcT6qtqpDgN1tjWuh5h39vl9R57c609SmYNI");
        formParams.put("client_id","0d7ea6734dbb48b0ad84304bc8189e37");
        formParams.put("client_secret","90b1cf13de3c48bf86700b3889711d3c");

      Response response = given().log().all().
                baseUri(System.getProperty("ACCOUNT_BASE_URI"))
                .contentType(ContentType.URLENC)
                .formParams(formParams)
      .when().post(API+TOKEN)
      .then().spec(getResponseSpec())
                .extract().response();

      return response;

    }

}
