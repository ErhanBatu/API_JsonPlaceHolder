
import com.google.gson.Gson;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;  //You have to have this import for validation
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.text.StringContent;
import java.util.*;


public class JsonSchemaValidation {

    @BeforeTest
    public void setUp(){

        baseURI="http://54.174.126.24:8000";

    }

    @Test
    public void test1(){

        given().accept(ContentType.JSON)
                .pathParam("id",17)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().body(matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));


        //this JsonSchemaValidation checks if id is int, name is string etc.

    }


}
