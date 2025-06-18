package postPayload2;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostRequestPayloadUsingPOJO {
    String id;
    @Test(priority = 1)
    void testPostUsingOrgJson(){
        RequestPOJO data = new RequestPOJO();
        data.setName("Scott");
        data.setLocation("France");
        data.setPhone("1234567890");
        String[] courseArr = {"C", "C++"};
        data.setCoursesArr(courseArr);

        /*
        given()
                .contentType("application/json; charset=utf-8")
                .body(data)
        .when()
                .post("http://localhost:3000/students")
        .then()
                .body("name",equalTo("Scott"))
                .body("location",equalTo("France"))
                .body("phone",equalTo("1234567890"))
                .body("coursesArr[0]",equalTo("C"))
                .body("coursesArr[1]",equalTo("C++"))
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
