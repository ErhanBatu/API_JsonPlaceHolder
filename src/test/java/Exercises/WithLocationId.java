package Exercises;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

public class WithLocationId {

    @BeforeClass
    public void setUp(){
        baseURI="https://jsonmock.hackerrank.com";
    }

    @Test
    public void test1(){
        List<Map<String,Object>> mapList = new ArrayList<>();

        Response response1 = given().accept(ContentType.JSON)
                .when().get("/api/transactions");

        int totalPage = response1.path("total_pages");
        int perPage = response1.path("per_page");
        int IDtotal = response1.path("total");


        for (int i = 1; i < 3; i++) {

            Response response2 = given().accept(ContentType.JSON)
//                    .queryParam("page", i)
                    .pathParam("id",i)
                    .when().get("/api/transactions/{id}");

            mapList.add(response2.path("data"));




        }

        




    }

}
