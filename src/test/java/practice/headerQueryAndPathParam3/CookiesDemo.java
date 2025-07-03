package practice.headerQueryAndPathParam3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CookiesDemo {
    @Test(priority=1)
    void testCookies(){
        given()
                .when().get("https://www.google.com/")
                .then()
                //.cookie("AEC","abc")
                .log().all();
    }
    @Test(priority=2)
    void getCookiesInfo(){
        Response res = given()
                .when().get("https://www.google.com/");
        //get single cookie info
        String cookie_value = res.getCookie("AEC");
        System.out.println("cookie val : "+cookie_value);
        //get all cookie info
        Map<String, String> cookie_values = res.getCookies();
        Set<Map.Entry<String, String>> entries = cookie_values.entrySet();
        for(Map.Entry<String,String> entry : entries){
            System.out.println(entry.getKey()+ " "+ entry.getValue());
        }

    }

}
