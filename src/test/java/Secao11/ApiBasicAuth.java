package Secao11;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

public class ApiBasicAuth {

    @Test
    public void acessarApiBasicAuthCenarioNaoAutorizado(){

        RestAssured
                .given()
                .log().all()
                .when()
                .get("https://restapi.wcaquino.me/basicauth")
                .then()
                .statusCode(401)
                .log().all()
        ;
    }

    @Test
    public void acessarApiBasicAuthCenario1Autorizado(){

        RestAssured
                .given()
                .log().all()
                .when()
                .get("https://admin:senha@restapi.wcaquino.me/basicauth")
                .then()
                .statusCode(200)
                .log().all()
        ;
    }

    @Test
    public void acessarApiBasicAuthCenario2Autorizado(){

        RestAssured
                .given()
                .log().all()
                .auth().basic( "admin", "senha" )
                .when()
                .get("https://restapi.wcaquino.me/basicauth")
                .then()
                .statusCode(200)
                .log().all()
        ;
    }

    @Test
    public void acessarApiBasicAuthCenario3Autorizado(){

        RestAssured
                .given()
                .log().all()
                .auth().preemptive().basic( "admin", "senha" )
                .when()
                .get("https://restapi.wcaquino.me/basicauth2")
                .then()
                .statusCode(200)
                .log().all()
        ;
    }
}
