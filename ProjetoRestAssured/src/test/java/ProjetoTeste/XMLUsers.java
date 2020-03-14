package ProjetoTeste;

import Properties.Properties;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

public class XMLUsers {

    @Test
    public void acessandoUsersComXML(){

        RestAssured
                .given()
                .when()
                    .get(Properties.URLUser3XML)
                .then()
                    .statusCode(200)
                    .body("user.name", Matchers.is("Ana Júlia"))


                ;
    }
}
