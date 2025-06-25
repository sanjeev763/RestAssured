package jsonXmlSchemaValidation6;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//json response(.json) --> json schema(.json)
public class JSONSchemaValidation {
    @Test(priority = 1)
    void jsonSchemaValidation(){

        given()
                .when()
                .get("http://localhost:3000/book")
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"));
    }

}
