package parseResponseBody4;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class ParsingJsonResponseData {
        @Test(priority = 1)
        void testJsonResponse(){
            //Approach 1
            given()
                    .contentType("application/json")
                    .when().get("http://localhost:3000/book")
                    .then()
                    .statusCode(200)
                    .header("Content-Type","application/json")
                    //Use json path finder
                    .body("[5].title", equalTo("World War II Chronicles"));

        }
        @Test(priority = 2)
        void testJSONResBody(){
            //Approach 2
            Response res = given()
                    .contentType("application/json")
                    .when().get("http://localhost:3000/book");
            Assert.assertEquals(res.getStatusCode(), 200);
            String title5 = res.jsonPath().get("[4].title").toString();
            Assert.assertEquals(title5, "The Hobbit");
        }
        @Test(priority = 3)
        void testJSONUsingList() {
            //Approach 3
            Response res = given()
                    .contentType("application/json")
                    .when().get("http://localhost:3000/book");
            Assert.assertEquals(res.getStatusCode(), 200);
            List<String> titleList = res.jsonPath().get("title");
            //the response from http://localhost:3000/book is a direct array (without the book key) due to json-server’s behavior
            // Since the response is a root-level array, we can use:
            //get("title"): Selects the title field from each object in the array.
            //get("*.title"): Explicitly selects title from all objects (works similarly)
            for (Object obj : titleList) {
                if (Objects.equals(obj.toString(), "The Hobbit")) {
                    System.out.println("found the title");
                }
            }
        }

    //@Test(priority = 4)
    void testJSONUsingList2() {
        //Approach 4
        Response res = given()
                .contentType("application/json")
                .when().get("http://localhost:3000/book");
        Assert.assertEquals(res.getStatusCode(), 200);
        //JSONObject class from org.json
        JSONObject jo = new JSONObject(res.toString()); //We can not directly pass the response in the JSONObject so convert it using toString
        int jsonArrayLen = jo.getJSONArray("book").length();
        for(int i=0; i < jsonArrayLen; i++){
            String val = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
            System.out.println(val);
        }

    }
    @Test(priority = 5)
    void testJSONUsingList3() {
        //Approach 4
        Response res = given()
                .contentType("application/json")
                .when().get("http://localhost:3000/book");
        Assert.assertEquals(res.getStatusCode(), 200);
        //parse the response in JSONArray
        JSONArray ja = new JSONArray(res.asString());
        int jsonArrayLen = ja.length();
        for(int i=0; i < jsonArrayLen; i++){
            JSONObject bookJSONObj = ja.getJSONObject(i);
            String titleVal = bookJSONObj.getString("title");
            System.out.println(titleVal);
        }

    }

}
