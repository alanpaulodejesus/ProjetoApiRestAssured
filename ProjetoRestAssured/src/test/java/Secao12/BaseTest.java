package Secao12;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;

public class BaseTest extends Constantes{


    @BeforeClass
    public static void setUp(){

        RestAssured.baseURI= APP_BASE_URL;
        //RestAssured.DEFAULT_PORT= APP_PORTA;
        RestAssured.basePath=APP_BASE_PATH;

        RequestSpecBuilder reBuilder = new RequestSpecBuilder();
        reBuilder.setContentType( APP_CONTENT_TYPE );
        RestAssured.requestSpecification = reBuilder.build();

        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectResponseTime( Matchers.lessThan( MAX_TEMP ) );
        RestAssured.responseSpecification = resBuilder.build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
