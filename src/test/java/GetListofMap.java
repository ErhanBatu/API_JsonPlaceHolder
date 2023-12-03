
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

public class GetListofMap {

    @BeforeClass
    public void setUp(){
        baseURI="https://jsonplaceholder.typicode.com/";
    }
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/users");

        List<Map<String,Object>> listofMap = response.body().as(List.class);

        System.out.println(listofMap);
        assertTrue(listofMap.get(1).containsValue("Ervin Howell"));


    }
}
