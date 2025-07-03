package practice.postPayload2;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostRequestPayloadUsingOrgJson {
    String id;
    @Test(priority = 1)
    void testPostUsingOrgJson(){
        JSONObject data = new JSONObject();
        data.put("name", "Sanju");
        data.put("location", "India");
        data.put("phone", "8098098090");
        String[] selectedcourses = {"C", "C++"};
        data.put("courses", selectedcourses);

        /*given()
                .contentType("application/json; charset=utf-8")
                .body(data.toString())
        .when()
                .post("http://localhost:3000/students")
        .then()
                .body("name",equalTo("Sanju"))
                .body("location",equalTo("India"))
                .body("phone",equalTo("8098098090"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("Content-Type", "application/json")
                .log().all();*/
        Response res = given()
                .contentType("application/json; charset=utf-8")
                .body(data.toString()) // Point to note that when using org.json we need to pass data as string
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
