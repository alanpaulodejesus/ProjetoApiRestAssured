package Secao1;

import Properties.Properties;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

public class OlaMundo {

    @Test
    public void teste(){

        Response retorno = RestAssured.request(Method.GET, Properties.URL);

        System.out.println(retorno.getBody().asString());
        System.out.println(retorno.getBody().asString().equals("Ola Mundo!"));
        System.out.println(retorno.statusCode());
        //Validações
        Assert.assertEquals( 200, retorno.statusCode());
        Assert.assertTrue(retorno.statusCode()==200);

        ValidatableResponse validResponse = retorno.then();
        validResponse.statusCode(200);
    }

    @Test
    public void formaAcessoRestAssured(){

        RestAssured.get(Properties.URL).then().statusCode(200);
    }

    @Test
    public void outraFormaRestAssured(){

        RestAssured
                .given()
                .when()
                    .get(Properties.URL)
                .then()
                    .statusCode(200)
        ;
    }
}

