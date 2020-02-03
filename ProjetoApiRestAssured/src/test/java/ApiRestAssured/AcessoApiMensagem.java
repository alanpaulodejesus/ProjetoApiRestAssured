package ApiRestAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import static Properties.Properties.URLacessoApi;
import static org.hamcrest.Matchers.*;

public class AcessoApiMensagem {

    @Test
    public void validarAcessoApiPratico(){

        RestAssured.
                given()
                .when()
                    .get(URLacessoApi)
                .then()
                    .statusCode( 200 );
    }

    @Test
    public void validarAcessoApiMod(){

        RestAssured.
                given()
                .when()
                    .get( URLacessoApi )
                .then()
                    .statusCode( 200 )
                    .body( Matchers.is( "Ola Mundo!" ))
                    .body( containsString("Mundo") )
                    .body( is( not( nullValue() ) ) );
    }

    @Test
    public void acessoApi(){

        Response resposta = RestAssured.request( Method.GET, URLacessoApi );
        System.out.println(resposta.getBody().asString());
        System.out.println( resposta.statusCode() );
        System.out.println( resposta.getBody().asString().equals("Ola Mundo!") );
        System.out.println( resposta.statusCode()==200 );

        ValidatableResponse validar = resposta.then();
        validar.statusCode( 200 );
    }


}
