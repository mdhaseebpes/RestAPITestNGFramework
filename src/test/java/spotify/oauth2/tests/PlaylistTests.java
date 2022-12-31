package spotify.oauth2.tests;

import io.qameta.allure.*;
import io.restassured.response.Response;

import org.testng.annotations.Test;
import spotify.oauth2.api.PlaylistApi;
import spotify.oauth2.api.StatusCode;
import spotify.oauth2.errorpojo.Error;
import spotify.oauth2.pojo.PlayList;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static spotify.oauth2.utils.FakerUtils.generateDescription;
import static spotify.oauth2.utils.FakerUtils.generateName;

@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
public class PlaylistTests extends BaseTest{

    @Step
    public PlayList playListBuilder(String name,String description, boolean _public){
        return PlayList.builder()
                .name(name)
                .description(description)
                ._public(_public)
                .build();
    /*    return new PlayList()
                .setName(name)
                .setDescription(description)
                .setPublic(_public);*/
    }

    @Step
    public void assertError(Error responseErr ,int expectedStatusCode, String expectedMsg){
        assertThat(responseErr.getError().getStatus(),equalTo(expectedStatusCode));
        assertThat(responseErr.getError().getMessage(),equalTo(expectedMsg));
    }

    @Story("Create a playlist story")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Description("this is the description")
    @Test(description = "should be able to create a playlist")
    public void shouldAbleToCreatePlayList(){
        //Request builder patter with Pojo's
        PlayList requestPlayList = playListBuilder(generateName(),
                generateDescription(),false);

     /*   String payLoad = "{\n" +
                "    \"name\": \"Automate API Playlist\",\n" +
                "    \"description\": \"Rest API to test\",\n" +
                "    \"public\": false\n" +
                "}";*/

       Response response = PlaylistApi.post(requestPlayList);
       PlayList responsePlayList = response.as(PlayList.class);

        assertThat(responsePlayList.getName(), equalTo(requestPlayList.getName()));
        assertThat(responsePlayList.getDescription(), equalTo(requestPlayList.getDescription()));
        assertThat(responsePlayList.get_public(), equalTo(requestPlayList.get_public()));

    }

    @Story("Create a playlist story")
    @Test
    public void getPlayList(){

       Response response = PlaylistApi.get("4qto4VhAGZtk0uhQpB1gz9");
       PlayList responsePlayList = response.as(PlayList.class);

        assertThat(responsePlayList.getName(),equalTo("Automate API Playlist") );
        assertThat(responsePlayList.getDescription(), equalTo("Rest API to test"));
        assertThat(responsePlayList.get_public(), equalTo(false));
    }

    @Test
    public void updatePlayList(){
        PlayList requestUpdatePlayList = playListBuilder("Automate V2",
                "Rest automate test",true);

    /*    String payload = "{\n" +
                "  \"name\": \"Automate v2\",\n" +
                "  \"description\": \"Rest Automate\",\n" +
                "  \"public\": true\n" +
                "}";*/

     given(getRequestSpec())
                .body(requestUpdatePlayList)
                .when().put("/playlists/41tTjuby5F7LP3miYmMpFF").
                then()
                .assertThat().statusCode(StatusCode.CODE_200.getCode());

    }

    @Test
    public void errorVerificationPlayList() {

        PlayList requestPlayList = playListBuilder("", generateDescription(), false);

        Response response = PlaylistApi.post(requestPlayList, "31vd4xn75kjau4nn752gxqn66yhm");
        assertError(response.as(Error.class), StatusCode.CODE_400.code, "Missing required field: name");

    }

}

