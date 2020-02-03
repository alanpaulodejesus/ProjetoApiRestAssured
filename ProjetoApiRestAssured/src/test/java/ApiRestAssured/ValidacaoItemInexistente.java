package ApiRestAssured;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

import static Properties.Properties.URLacessoInexistente;

public class ValidacaoItemInexistente {

    @Test
    public void verificaItemInexistente(){

        RestAssured
                .given()
                .when()
                    .get(URLacessoInexistente )
                .then()
                    .statusCode( 404 )
                    .body( "error", Matchers.is( "Usuário inexistente" ));
    }
}
