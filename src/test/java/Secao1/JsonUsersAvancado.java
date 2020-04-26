package Secao1;

import Properties.Properties;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class JsonUsersAvancado {

    @Test
    public void usuariosComMenosde25MaiorQue20(){

        RestAssured
                .given()
                .when()
                .get(Properties.URLUsers)
                .then()
                .statusCode(200)
                .body("$", Matchers.hasSize(3))
                .body("age.findAll{it<=25 && it >20}", Matchers.hasSize(1))
                .body("findAll{it.age<=25 && it.age >20}.name", Matchers.hasItem("Maria Joaquina")) //Retorna o nome da pessoa
                .body("findAll{it.age<=25 && it.age >20}[0].name", Matchers.is("Maria Joaquina")) //Forma de transformar em is "OBJETO"
                .body("findAll{it.name.contains('n')}.name", Matchers.hasItems("Maria Joaquina", "Ana Júlia"))  //====>> Nome com a letra N
                .body("findAll{it.name.length() > 10}.name", Matchers.hasItems("Maria Joaquina", "João da Silva"))  //===> Nome com mais de 10 caracteres
                .body("name.collect{it.toUpperCase()}", Matchers.hasItems("MARIA JOAQUINA"))
                .body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}", Matchers.hasItems("MARIA JOAQUINA")) //===>> Captura nomes que começam com maria e alteram para Maiúsculo
                .body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}.toArray()", Matchers.allOf(Matchers.arrayContaining("MARIA JOAQUINA"), Matchers.arrayWithSize(1))) //===>> Verifica que o item aciam só contém um
                .body("age.collect{it*2}", Matchers.hasItems(60,50,40)) //===>> Multiplica por dois as idades de cada user
                .body("id.max()", Matchers.is(3)) //==>> Retorna maior idade
                .body("salary.min()", Matchers.is(1234.5678f)) //===>> Retorna menor salario
                .body("salary.findAll{it != null}.sum()", Matchers.is(Matchers.closeTo(3734.5678f, 0.001))) //===> Soma de salarios
                .body("salary.findAll{it != null}.sum()", Matchers.allOf(Matchers.greaterThan(3000d), Matchers.lessThan(5000d))) //===> soma salarios e retorna se ha menor que 3000 e maior que 5000
        ;
    }

    @Test
    public void usuariosComMenosde25(){

        RestAssured
                .given()
                .when()
                .get(Properties.URLUsers)
                .then()
                .statusCode(200)
                .body("$", Matchers.hasSize(3))
                .body("age.findAll{it<=25}", Matchers.hasSize(2))
        ;
    }

    @Test
    public void unindoJapathComJava(){

        ArrayList<String> names=
        RestAssured
                .given()
                .when()
                    .get(Properties.URLUsers)
                .then()
                    .statusCode(200)
                    .extract().path("name.findAll{it.startsWith('Maria')}")
                ;

        Assert.assertEquals(1, names.size()); //===> verifica se existe apenas um
        Assert.assertTrue(names.get(0).equalsIgnoreCase("mAriA JoaQuina")); //===> verifica se na primeira posição está o nome ignorando letra mai/min
        Assert.assertEquals(names.get(0).toUpperCase(), "maria joaquina".toUpperCase()); //===> verifica se na primeira posição está o nome ambos comparando maiusculo
    }
}
