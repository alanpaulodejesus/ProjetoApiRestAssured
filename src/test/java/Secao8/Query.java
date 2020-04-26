package Secao8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

public class Query {

    @Test
    public void acessandoPorQuery(){

        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("https://restapi.wcaquino.me/v2/users?format=xml")
                .then()
                    .log().all()
                    .statusCode(200)
                    .contentType(ContentType.XML)
                ;
    }



}
