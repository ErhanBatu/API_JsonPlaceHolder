import io.restassured.http.ContentType;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GetAllLng {

    @BeforeClass
    public void setUp(){
        baseURI="https://jsonplaceholder.typicode.com/";
    }

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/users");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");

        List<String> listOfAllLng = new ArrayList<>();

        JsonPath jsonPath = response.jsonPath();
        listOfAllLng=jsonPath.get("address.geo.lng");

        System.out.println(listOfAllLng);


    }

}
