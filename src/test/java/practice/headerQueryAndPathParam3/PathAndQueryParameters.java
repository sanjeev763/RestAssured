package practice.headerQueryAndPathParam3;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameters {
    @Test
    void testPathAndQuery(){
        given()
                .pathParams("mypath","users") // mypath(anything) is given from user's end
                .queryParam("page",2)
                .queryParam("id", 5)
                .header("x-api-key", "reqres-free-v1")
                .when()
                .get("https://reqres.in/api/{mypath}")//only path parameter is passed using curley brackets and not the query params
                .then()
                .statusCode(200)
                .log().all();
    }
}
