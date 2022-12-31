package spotify.oauth2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static spotify.oauth2.api.Route.BASE_PATH;

public class SpecBuilder {

   // static String access_token = "BQCXd-27qCaX1LvQAO70vNAP0EjJgh7TYf7bxOxdHC62RLof82GgOLxvU0jrmR0Ba11d9q5MC8P10vhnFCXWyHpqDqIrEDWnxsnnlrHjCs2ctL80M7o6RYtv5cteA-rYeTRl0xyoxSAnX9YSDJeumydcd8bszeaw2hWVsXuCJddb6zJGt2YqLzgSQfgMluojgmPlzxTYBT0yPeYiFgWbT3TbnRYGtUJ0e2Pk7muQOkiabNlPtOiLMKJtOGNgR_qPaIpTWMqYenJMHFaV";
    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("BASE_URI"))
               // .setBaseUri("https://api.spotify.com")
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL).build();

    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL).build();

    }

    public static RequestSpecification getAccountSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("ACCOUNT_BASE_URI"))
                //.setBaseUri("https://accounts.spotify.com")
                .setContentType(ContentType.URLENC)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL).build();
    }
}
