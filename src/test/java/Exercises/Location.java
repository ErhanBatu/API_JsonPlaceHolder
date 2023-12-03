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

public class Location {

    @BeforeClass
    public void setUp(){
        baseURI="https://jsonmock.hackerrank.com";
    }

    @Test
    public void test1(){

        List<String> amount = new ArrayList<>();
        List<Integer> totalAmount = new ArrayList<>();

        Response response1 = given().accept(ContentType.JSON)
                .given().get("/api/transactions");

        int totalPage = response1.path("total_pages");

        System.out.println(totalPage);

        //------------------------------------------------------


        for (int i = 0; i < totalPage; i++) {

            Response response2 = given().accept(ContentType.JSON)
                    .queryParam("page",i+1)
                    .when().get("/api/transactions");

            List<Integer> locationsID = new ArrayList<>();

            locationsID = response2.path("data.location.id");

            //-----------------------------------------------------

            for (int j = 0; j < locationsID.size(); j++) {

                if(locationsID.get(j)==1){

                    Response response3 = given().accept(ContentType.JSON)
                            .pathParam("id", j + 1 + i * 10)
                            .queryParam("txnType", "debit")
                            .queryParam("page", i + 1)
                            .when().get("/api/transactions/{id}");

                    amount.add(response3.path("data.amount"));
                    String str = "";
                    double dummy = 0;

                    for (int k = 0; k < amount.size(); k++) {

                        str=amount.get(k).replace("$","").replace(",","");
                        dummy= Double.parseDouble(str);
                        
                    }
                    totalAmount.add(response3.path("data.userId"));
                    totalAmount.add((int)dummy);


                }

            }




        }

        List<List<Integer>> sumList = new ArrayList<>();
        List<Integer> sum = new ArrayList<>();
        int dummy = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j<totalAmount.size();j++){
                if(totalAmount.get(j)==i+1){
                    dummy = dummy + totalAmount.get(j+1);

                }

            }
            sum.add(i+1);
            sum.add(dummy);
            sumList.add(sum);
        }

        System.out.println(sumList);

    }




}
