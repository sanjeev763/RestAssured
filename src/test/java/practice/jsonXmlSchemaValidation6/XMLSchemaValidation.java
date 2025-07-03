package practice.jsonXmlSchemaValidation6;

import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//xml response (.xml) --> xml schema(.xsd)
public class XMLSchemaValidation {
    @Test(priority = 1)
    void xmlSchemaValidation(){
        given()
                .when()
                .get("https://www.w3schools.com/xml/plant_catalog.xml")
                .then()
                .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("storeXmlSchema.xsd"));
    }
}
