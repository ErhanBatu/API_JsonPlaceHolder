package Exercises;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.*;

public class WithLocations {

    @BeforeClass
    public void setUp(){
        baseURI="https://jsonmock.hackerrank.com";
    }

    @Test
    public void test1(){

        List<List<Integer>> UserAndAmount = new ArrayList<>();

        List<String> amount = new ArrayList<>();

        Response response1 = given().accept(ContentType.JSON)
                .when().get("/api/transactions");

        int totalPage = response1.path("total_pages");

// -------------------------------------------------------------------

        for (int i = 0; i < totalPage; i++) {

            Response response2 = given().accept(ContentType.JSON)
                    .queryParam("page",i+1)
                    .when().get("/api/transactions");

            List<Integer> locationsID = new ArrayList<>();

            locationsID = response2.path("data.location.id");

            //------------------------------------------------------------------

            for (int j = 0; j < locationsID.size(); j++) {
                if (locationsID.get(j)==1){

                    Response response3 = given().accept(ContentType.JSON)
                            .pathParam("id", j + 1 +i*10)
                            .queryParam("txnType", "debit")
                            .queryParam("page",i+1)
                            .when().get("/api/transactions/{id}");

                    amount.add(response3.path("data.amount"));

                    String str;
                    double dummy = 0;
                    int userId = response3.path("data.userId");


                    for (int k = 0; k < amount.size(); k++) {

                        str = amount.get(k).replace("$","").replace(",","");
                        dummy=Double.parseDouble(str);

                    }

                    List<Integer> userAmount = new ArrayList<>();
                    userAmount.add(userId);
                    userAmount.add((int)dummy);

                    UserAndAmount.add(userAmount);

                }

            }

        }

        System.out.println(UserAndAmount);

        //-------------------------------------------------------------

        List<List<Integer>> last = new ArrayList<>();

        int dummy = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j<UserAndAmount.size(); j++){
                if (UserAndAmount.get(j).get(0)==i+1){
                    dummy=dummy+UserAndAmount.get(i).get(1);
                }
            }
            List<Integer> userAndSumAmount = new ArrayList<>();
            userAndSumAmount.add(i+1);
            userAndSumAmount.add(dummy);
            last.add(userAndSumAmount);

        }

        System.out.println(last);
    }


}
