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

public class TwoDArray {

    @BeforeClass
    public void setUp(){
        baseURI="https://jsonmock.hackerrank.com";
    }

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .queryParam("page",1)
                .when().get("/api/transactions");

        assertEquals(response.statusCode(),200);

        List<Integer> userId = new ArrayList<>();
        userId = response.path("data.userId");

        Set<Integer> size = new HashSet<>();
        for (int i = 0; i < userId.size(); i++) {
             size.add(userId.get(i));

        }


        List<Integer> totalUsers = new ArrayList<>();
        List<List<Integer>> newAmount = new ArrayList<>();

        //extra List
        List<Integer> extra = new ArrayList<>();
        int[][] last = new int[size.size()][2];


        for (int i = 1; i < size.size()+1; i++) {

            Response response1 = given().accept(ContentType.JSON)
                    .queryParam("page", 1)
                    .queryParam("userId", i)
                    .when().get("/api/transactions");

            List<String> amount = new ArrayList<>();
            amount = response1.path("data.amount");

            double sum=0;

            for (int j = 0; j < amount.size(); j++) {

                String str = amount.get(j).replace("$","").replace(",","");
                sum=sum+Double.parseDouble(str);
            }

            List<Integer> usersAndAmount = new ArrayList<>();
            usersAndAmount.add(i);
            usersAndAmount.add((int)(sum));

            newAmount.add(usersAndAmount);

            extra.add((int)(sum));


                last[i-1][0] = i;
                last[i-1][1] = (int)(sum);


        }

        System.out.println(newAmount);
        System.out.println(Arrays.toString(last[0]));







    }




}
