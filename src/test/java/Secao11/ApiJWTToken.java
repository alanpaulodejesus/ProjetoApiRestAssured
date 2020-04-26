package Secao11;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ApiJWTToken {

    @Test
    public void acessarApiSemAutorizacao(){

        RestAssured
                .given()
                    .log().all()
                .when()
                .get("https://barrigarest.wcaquino.me/contas")
                .then()
                .statusCode(401)
                .log().all()
        ;
    }

    @Test
    public void acessarApiComToken (){


        Map<String, String> login = new HashMap<String, String>( );
        login.put( "email", "alan@jesus" );
        login.put( "senha", "123" );


        String token = RestAssured
                .given()
                    .log().all()
                    .body( login )
                    .contentType( ContentType.JSON )
                .when()
                    .post("https://seubarriga.wcaquino.me/signin")
                .then()
                    .statusCode(200)
                    .log().all()
                    .extract().path( "token" )
        ;

        RestAssured
                .given()
                    .log().all()
                    .header( "Authorization", "JWT " +token )
                .when()
                    .get("https://barrigarest.wcaquino.me/contas")
                .then()
                    .log().all()
                    .statusCode( 200 )
                    .body( "name", Matchers.hasItem( "teste123" ) )
                ;

    }
}
