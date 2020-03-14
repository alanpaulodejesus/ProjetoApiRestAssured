package ProjetoTeste;

import Properties.Properties;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class JsonUsers {

    @Test
    public void verificaUsuarioPrimeiroNivel(){
        //OBS.: Hancrest dentro do body

        RestAssured
                .given()
                .when()
                    .get(Properties.URLUser1)
                .then()
                    .statusCode(200)
                    .body("id", Matchers.is(1))
                    .body("name", Matchers.containsString("Silva"))
                    .body("age", Matchers.greaterThan(18)) //Maior que
                ;
    }

    @Test
    public void outraFormaVerificarUsuarioPath(){
        Response retorno = RestAssured.request(Method.GET, Properties.URLUser1);

        //PATH
        Assert.assertEquals(new Integer(1), retorno.path("id"));
        Assert.assertEquals(new Integer(1), retorno.path("%s","id"));

        //JSONPATH
        JsonPath jsonPath = new JsonPath(retorno.asString());
        Assert.assertEquals(1, jsonPath.getInt("id"));

        //From
        int id = JsonPath.from(retorno.asString()).getInt("id");
        Assert.assertEquals(1, id);
    }

    @Test
    public void verificaUsuarioSegundoNivel(){
        //OBS.: Hancrest dentro do body

        RestAssured
                .given()
                .when()
                    .get(Properties.URLUser2)
                .then()
                    .statusCode(200)
                    .body("name", Matchers.containsString("Joaquina"))
                    .body("endereco.rua", Matchers.is("Rua dos bobos"))
        ;
    }

    @Test
    public void verificaUsuarioTerceiroNivel(){
        //OBS.: Hancrest dentro do body

        RestAssured
                .given()
                .when()
                .get(Properties.URLUser3)
                .then()
                .statusCode(200)
                .body("name", Matchers.containsString("Ana"))
                .body("filhos", Matchers.hasSize(2))
                .body("filhos.name[0]", Matchers.containsString("Zezinho"))
                .body("filhos.name[1]", Matchers.containsString("Luizinho"))
                .body("filhos.name", Matchers.hasItems("Luizinho", "Zezinho"))
        ;
    }

    @Test
    public void verificaUsuarioInexistente(){
        //OBS.: Hancrest dentro do body

        RestAssured
                .given()
                .when()
                .get(Properties.URLUser4)
                .then()
                .statusCode(404)
                .body("error", Matchers.containsString("Usuário inexistente"))
        ;
    }

    @Test
    public void verificaJsonNaRaiz(){

        RestAssured
                .given()
                .when()
                .get(Properties.URLUsers)
                .then()
                .statusCode(200)
                .body("$", Matchers.hasSize(3))
                .body("name", Matchers.hasItems("João da Silva", "Maria Joaquina", "Ana Júlia"))
                .body("age[1]",Matchers.is(25) )
                .body("filhos.name", Matchers.hasItems(Arrays.asList("Zezinho", "Luizinho")) )
        ;
    }
}
