package spotify.oauth2.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import spotify.oauth2.pojo.PlayList;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static spotify.oauth2.api.SpecBuilder.*;

public class ResourceApi {

    public static Response post(String path,String token,Object requestPayLoad){
        return given(getRequestSpec()).
                body(requestPayLoad).
                auth().oauth2(token).
               // header("Authorization","Bearer "+token)
                when().post(path).
                then().spec(getResponseSpec()).
                extract().response();
    }


    public static Response get(String path ,String token){
        return given(getRequestSpec()).
                auth().oauth2(token)
                //header("Authorization","Bearer "+token)
                .when().get(path).then().spec(getResponseSpec())
                .extract().response();

    }

    public static  Response postAccount(HashMap<String,String> formParams){
        return given(getAccountSpec())
                .formParams(formParams)
                .when().post("/api/token")
                .then().spec(getResponseSpec())
                .extract().response();
    }
}
