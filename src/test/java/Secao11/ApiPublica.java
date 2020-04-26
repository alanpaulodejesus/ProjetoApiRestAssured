package Secao11;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

public class ApiPublica {

    @Test
    public void acessarApiPublica(){

        RestAssured
                .given()
                    .log().all()
                .when()
                .get("https://swapi.co/api/people/1")
                .then()
                .statusCode(200)
                .log().all()
                .body("name", Matchers.is("Luke Skywalker"))
        ;
    }
}
