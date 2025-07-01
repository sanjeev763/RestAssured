package authorization7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class AuthenticationDemo7 {
    //Authentication - User is valid or not(user's credential is valid or not), First level of security
    //Authorization - Authenticated User has the permission to access a particular resource or not
    /*
     Rest supported authentication -
     Basic,
     Digest,
     Pre-emptive,
     (All the 3 above authentications use username & password)
     ======
     Bearer token, - It is passed in request header with a keyword Bearer(But depends how the dev has designed)
     oAuth1.0 - "consumerkey","consumersecret", "accesstoken", "tokenSecret" are needed.
     oAuth2.0 - only access token is required
     API Key -
    */
    @Test(priority = 1)
    void BasicAuthTest(){
        given()
                .auth().basic("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200);
    }
    @Test(priority = 2)
    void DigestAuthTest(){
        given()
                .auth().digest("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200);
    }
    @Test(priority = 3)
    void PreemptiveAuthTest(){
        given()
                .auth().preemptive().basic("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200);
    }

    @Test(priority = 4)
    void BearerTokenAuthTest(){
        String token = "abcd"; //add git token here
        given()
                .headers("Authorization","Bearer "+token)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 5)
    void testOAuth1(){
        given()
                .auth().oauth("consumerkey","consumersecret", "accesstoken", "tokenSecret")
                .when()
                .get("url")
                .then()
                .statusCode(200);
    }

    @Test(priority = 6)
    void testOAuth2(){
        given()
                .auth().oauth2("accessToken") //provide access token here
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 7)
    void testAPIKey(){
        String APIKey = "fa562037074883c94fc394fc5144487a";
        given()
                .queryParam("q","Delhi")
                .queryParam("appid",APIKey) //appid is APIKey
                .when()
                .get("https://api.openweathermap.org/data/2.5/weather")
                .then()
                .statusCode(200)
                .log().all();
    }


}
