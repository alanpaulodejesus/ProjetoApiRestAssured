package ApiRestAssured;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

import static Properties.Properties.URLacessoUsuario2;
import static Properties.Properties.URLacessoUsuario3;
import static org.hamcrest.Matchers.*;

public class AcessoApiUsuario {

    @Test
    public void acessoUsuarioPrimeiroNivel(){

        RestAssured
                .given()
                .when()
                    .get( URLacessoUsuario2 )
                .then()
                    .statusCode( 200 )
                    .body( "id", Matchers.is( 1 ))
                    .body( "name", containsString("silva") )
                    .body( "age", greaterThan( 18 ) )
        ;
    }

    @Test
    public void acessoUsuarioSegundoNivel(){

        RestAssured
                .given()
                .when()
                    .get( URLacessoUsuario2 )
                .then()
                    .statusCode( 200 )
                    .body( "name", containsString( "Joaquina"))
                    .body( "endereco.rua", is( "Rua dos bobos" ))
        ;
    }

    @Test
    public  void acessoUsuarioJsonComLista(){

        RestAssured
                .given()
                .when()
                    .get( URLacessoUsuario3 )
                .then()
                    .statusCode( 200 )
                    .body( "name", containsString( "Ana" ))
                    .body( "filhos", hasSize( 2 ))
                    .body( "filhos[0].name", is( "Zezinho"))
                    .body( "filhos.name", hasItem( "Zezinho" ));
    }
}
