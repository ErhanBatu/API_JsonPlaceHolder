
import com.google.gson.Gson;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class simpleGet_Users {

    @BeforeClass
    public void setUp(){
        baseURI="https://jsonplaceholder.typicode.com/";
    }

    @Test
    public void test1(){

            given().accept(ContentType.JSON)
                .pathParam("id", 1)
                .when().get("/users/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json; charset=utf-8")
                .and().assertThat().body("name",equalTo("Leanne Graham"));


    }

    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 1)
                .when().get("users/{id}");

        assertEquals(response.statusCode(),200,"not valid status code");
        assertEquals(response.contentType(),"application/json; charset=utf-8");


    }

}
