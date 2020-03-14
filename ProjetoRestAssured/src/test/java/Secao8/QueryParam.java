package Secao8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

public class QueryParam {


    @Test
    public void acessandoPorQueryParam(){

        RestAssured
                .given()
                .log().all()
                .queryParam("format", "xml")
                .when()
                .get("https://restapi.wcaquino.me/v2/users")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.XML)
                .contentType(Matchers.containsString("utf-8"))
        ;
    }
}
