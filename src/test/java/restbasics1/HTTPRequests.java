package restbasics1;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;


/*
given : content type, set cookies, add auth, add param, set headers info etc...
when : get, put, post, delete
then : validate status code, extract response, extract headers cookies & response body
*/

public class HTTPRequests {
    int id;

    @Test(priority = 1)
    void getUser(){
       given()
               .when()
                    .get("https://reqres.in/api/users?page=2")
               .then()
                    .statusCode(200)
                    .body("page", equalTo(2))
                    .log().all();
    }

    @Test(priority = 2)
    void createUser(){
        HashMap<String, String> hm = new HashMap<>();
        hm.put("name", "sanjeev");
        hm.put("job", "student");

        Response res = given()
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .body(hm)
        .when()
                .post("https://reqres.in/api/users")
        .then()
                .statusCode(201)
                .body("name",equalTo("sanjeev"))
                .log().all().extract().response();
        id = res.jsonPath().getInt("id");
        System.out.println("id : "+id);
    }

    @Test(priority = 3, dependsOnMethods = {"createUser"})
    void updateUser(){
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "sanjeev");
        data.put("job", "teacher");

        given()
            .contentType("application/json")
            .header("x-api-key", "reqres-free-v1")
            .body(data)
        .when()
            .put("https://reqres.in/api/users/"+id)
        .then()
            .statusCode(200)
            .body("job", equalTo("teacher"))
            .log().all();
    }

    @Test(priority = 4)
    void deleteUser(){
        given()
                .header("x-api-key", "reqres-free-v1")
        .when()
            .delete("https://reqres.in/api/users/"+id)
        .then()
            .statusCode(204)
            .log().all();
    }



}
