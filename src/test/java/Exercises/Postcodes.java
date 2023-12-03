package Exercises;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Postcodes {

    @BeforeClass
    public void setUp(){
        baseURI="http://postcodes.io//postcodes/CB3 0FA";
    }

    @Test
    public void test(){





    }


}
