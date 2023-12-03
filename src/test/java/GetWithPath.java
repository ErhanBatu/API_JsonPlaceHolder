import com.google.gson.Gson;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;


public class GetWithPath {

    @BeforeClass
    public void setUp(){
        baseURI="https://jsonplaceholder.typicode.com/";
    }

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 1)
                .when().get("/users/{id}");

        assertEquals(response.path("address.geo.lat"),"-37.3159");


    }
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/users");

        List<String> names = new ArrayList<>();

        names = response.path("name");
        System.out.println(names);

        Set<String> newnames = new HashSet<>();

        for (int i = 0; i < names.size()-1; i++) {
            for(int y = i+1; y<names.size();y++){

                if(names.get(y).substring(0,1).equals(names.get(i).substring(0,1))){
                    newnames.add(names.get(i));
                    newnames.add(names.get(y));
                }
            }
        }

        System.out.println(newnames);


    }

}
