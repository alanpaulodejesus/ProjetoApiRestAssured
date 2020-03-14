package Secao11;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

public class ApiPublicaComChave {

    @Test
    public void acessarApiPublicaComChave(){

        RestAssured
                .given()
                    .log().all()
                    .queryParam("q", "Fortaleza,br")
                    .queryParam("appid", "9d0cf526721761775f9e5e312624effd")
                .when()
                    .get("http://api.openweathermap.org/data/2.5/weather")
                .then()
                .statusCode(200)
                .body("name", Matchers.is( "Fortaleza" ))
                .body( "coord.lon", Matchers.is( -38.52f ))
                .body( "main.temp", Matchers.is( 300.46f ) )
                .log().all()
        ;
    }
}
