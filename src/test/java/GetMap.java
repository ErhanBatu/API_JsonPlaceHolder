
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;


public class GetMap {

    @BeforeClass
    public void setUp(){
        baseURI="https://jsonplaceholder.typicode.com/";
    }

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 3)
                .when().get("/users/{id}");

        Map<String,Object> oneMap = response.body().as(Map.class);

        System.out.println(oneMap);

        System.out.println(oneMap.get("username"));
        System.out.println(oneMap.get("suite"));//burda suite ulasamiyorum, nasil ulasabilirim?

    }
}
