package spotify.oauth2.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import spotify.oauth2.pojo.PlayList;

import static spotify.oauth2.api.Route.PLAYLISTS;
import static spotify.oauth2.api.Route.USERS;
import static spotify.oauth2.token.AccessToken.getToken;


public class PlaylistApi {

   // static String access_token= "BQBYsX2tLmkv6xfmm16_s2Cto-w2Sjsz_u_KMFRDAxUUaUHgaKN28hNVETUWeWPVw1TsWalZws2jgiyt97I7VivvqRt7tAAenGHShlmvTUAoaxE1Sc8nh0dROC2g6yHPIhLYqGkr9LRIS44lQsrzZ8nUqzufFwqlTHGa6ZAXqhiZAq4i98hLNt3083w0GaljxpjayH4HFFMJKNLIhZhnWyfb1_2ZiqjXFTwC87Odbjkub-lwHicWWVOMGW-vkrQSN0AjQP9talsVnUtG";

    public static Response post(PlayList requestPlayList){
       return ResourceApi.
               post(USERS +"/31vd4xn75kjau4nn752gxqn66yhm" + PLAYLISTS,getToken(),requestPlayList);
    }


  @Step
    public static Response post(PlayList requestPlayList, String userID){
       return ResourceApi.post(USERS+"/"+userID+""+PLAYLISTS,getToken(),requestPlayList);
    }


    @Step
    public static Response get(String playlistId){
       return ResourceApi.get(PLAYLISTS+"/"+playlistId,getToken());

    }

}
