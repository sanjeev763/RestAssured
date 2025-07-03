package practice.parseXMLResponseBody5;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.Test;

import javax.xml.crypto.dsig.XMLObject;

import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class parsingXMLResponse {
    @Test(priority = 1)
    void testXMLResponse(){
        //Approach 1
        given()
                .when().get("https://rss.nytimes.com/services/xml/rss/nyt/Technology.xml")
                .then().statusCode(200)
                .header("Content-Type", "application/xml")
                .body("rss.channel.link[0]", equalTo("https://www.nytimes.com/section/technology"));

    }
    @Test(priority=2)
    void testXMLResponse2(){
        //Approach 2
        Response res = given()
                .when().get("https://rss.nytimes.com/services/xml/rss/nyt/Technology.xml");

        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.header("Content-Type"), "application/xml");
        System.out.println(res.xmlPath().get("rss.channel.link[0]").toString());
    }

    @Test(priority=3)
    void testXMLResponse3(){
        //Approach 3
        Response res = given()
                .when().get("https://rss.nytimes.com/services/xml/rss/nyt/Technology.xml");

        XmlPath xmlobj = new XmlPath(res.asString());
        List<String> itemList = xmlobj.getList("rss.channel.item.title");
        System.out.println(itemList.size());
        for(String s : itemList){
            if(s.contains("Brazil")){
                System.out.println("Found Brazil....");
                break;
            }
        }

    }
}
