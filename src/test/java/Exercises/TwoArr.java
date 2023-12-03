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

public class TwoArr {

    @BeforeClass
    public void setUp(){
        baseURI="https://jsonmock.hackerrank.com";
    }

    @Test
    public void test1(){

        List<List<Integer>> lists = new ArrayList<>();

        Response response = given().accept(ContentType.JSON)
                .queryParam("page", 1)
                .when().get("/api/transactions");

        List<Integer> userIds = new ArrayList<>();
        userIds = response.path("data.userId");


        Set<Integer> userId = new HashSet<>();

        for (int i = 0; i < userIds.size(); i++) {

            userId.add(userIds.get(i));
        }

        System.out.println(userId);










        for (int i = 1; i < userId.size()+1; i++) {

            Response response1 = given().accept(ContentType.JSON)
                    .queryParam("page", 1)
                    .queryParam("userId", i)
                    .when().get("/api/transactions");


            List<String> amount = new ArrayList<>();
            amount=response1.path("data.amount");

            double sum = 0;
            String str;


            for (int j = 0; j < amount.size(); j++) {

                str = amount.get(j).replace("$","").replace(",","");

                sum = sum + Double.parseDouble(str);
            }

            List<Integer> userAndAmount = new ArrayList<>();
            userAndAmount.add(i);
            userAndAmount.add((int)sum);

            lists.add(userAndAmount);

        }

        System.out.println(lists);


    }
}
