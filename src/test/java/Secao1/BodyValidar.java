package Secao1;

import Properties.Properties;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

public class BodyValidar {


    @Test
    public void validarBody(){

        RestAssured
                .given()
                .when()
                    .get(Properties.URL)
                .then()
                    .statusCode(400)
                    .body(Matchers.is("Ola Mundo!"))
                    .body(Matchers.containsString("Mundo")) //Verifica se possui uma palavra
                    .body(Matchers.is(Matchers.notNullValue())) //Verifica que não está vazio
        ;
    }
}

