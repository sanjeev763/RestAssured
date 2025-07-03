package practice.postPayload2;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

/*
1 Using HashMap
2 Using org.json
3 Using POJO
4 Using external Json file
*/
public class PostRequestPayloadUsingHashMap{
    String id;
    @Test(priority = 1)
    void testPostUsingHashMap(){
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "Sanju");
        data.put("location", "India");
        data.put("phone", "8098098090");
        String[] selectedcourses = {"C", "C++"};
        data.put("courses", selectedcourses);

        /*
        given()
                .contentType("application/json; charset=utf-8")
                .body(data)
        .when()
                .post("http://localhost:3000/students")
        .then()
                .body("name",equalTo("Sanju"))
                .body("location",equalTo("India"))
                .body("phone",equalTo("8098098090"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("Content-Type", "application/json")
                .log().all();
                */
        Response res = given()
                .contentType("application/json; charset=utf-8")
                .body(data)
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
