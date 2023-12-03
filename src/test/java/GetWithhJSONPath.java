
import com.google.gson.Gson;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.text.StringContent;
import java.util.*;

public class GetWithhJSONPath {

    @BeforeClass
    public void setUp(){
        baseURI="https://jsonplaceholder.typicode.com/";
    }

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 2)
                .when().get("/users/{id}");

        JsonPath aaa = response.jsonPath();

        String name = aaa.getString("name");
        assertEquals(name, "Ervin Howell");


        System.out.println(response.path("name").toString());

        String xx = response.path("name");



    }
}
