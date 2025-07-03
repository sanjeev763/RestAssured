package practice.postPayload2;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;

public class PostRequestPayloadUsingExternalJsonFile {
    String id;
    @Test(priority = 1)
    void testPostUsingExternalJsonFile() throws FileNotFoundException {
        File file = new File(".\\body.json");
        FileReader fr = new FileReader(file);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);


        /*given()
                    .contentType("application/json; charset=utf-8")
                    .body(data.toString())
            .when()
                    .post("http://localhost:3000/students")
            .then()
                    .body("name",equalTo("Scott"))
                .body("location",equalTo("France"))
                .body("phone",equalTo("9876543210"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("Content-Type", "application/json")
                    .log().all();*/


        Response res = given()
                .contentType("application/json; charset=utf-8")
                .body(data.toString())
                .when()
                .post("http://localhost:3000/students");

        id = res.jsonPath().getString("id");
            System.out.println("id is - " +id);
    }

    @Test(priority = 2)
    void testDelete(){
        given()
                .when().delete("http://localhost:3000/students/"+id)
                .then().statusCode(200);
    }
}
