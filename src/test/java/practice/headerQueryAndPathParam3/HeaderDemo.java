package practice.headerQueryAndPathParam3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class HeaderDemo {
    @Test(priority=1)
    void testHeaders(){
        given()
                .when().get("https://www.google.com/")
                .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .and() //optional
                .header("Server", "gws")
                .and() //optional
                .header("Content-Encoding", "gzip")
                .log().all();
    }

    @Test(priority=2)
    void getHeaderInfo(){
        Response res = given()
                .when().get("https://www.google.com/");
        //get single header info
        String header_value = res.getHeader("Server");
        System.out.println("header val : "+header_value);
        //get all headers info
        Headers header_values = res.getHeaders();
        for(Header header : header_values){
            System.out.println(header.getName()+" "+header.getValue());
        }

    }

    @Test(priority=3)
    void logAllHeaders(){
        given()
                .when().get("https://www.google.com/")
                .then()
                .log().headers();
    }
}
