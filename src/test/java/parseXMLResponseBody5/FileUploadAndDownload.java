package parseXMLResponseBody5;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class FileUploadAndDownload {
    //@Test(priority = 1)
    void singleFileUpload(){
        File myfile = new File("D:\\CSE\\Practice\\RestAssuredPrac\\text1.txt");
        //In order to upload this local file run java -jar file-upload-RestAPI.jar in terminal
        //check localhost:8080
        //file upload will be in the root folder of the path given for jar upload
        given()
                .multiPart("file",myfile) //this represents the form-data
                .contentType("multipart/form-data")
                .when()
                .post("http://localhost:8080/uploadFile")
                .then()
                .statusCode(200)
                .body("fileName",equalTo("text1.txt"))
                .log().all();
    }
    //@Test(priority = 2)
    void multipleFileUpload(){
        File myfile1 = new File("D:\\CSE\\Practice\\RestAssuredPrac\\text1.txt");
        File myfile2 = new File("D:\\CSE\\Practice\\RestAssuredPrac\\text2.txt");
        //In order to upload this local file run java -jar file-upload-RestAPI.jar in terminal
        //check localhost:8080
        given()
                .multiPart("files",myfile1) //this represents the form-data
                .multiPart("files",myfile2) //this represents the form-data
                .contentType("multipart/form-data")
                .when()
                .post("http://localhost:8080/uploadMultipleFiles")
                .then()
                .statusCode(200)
                .body("[0].fileName",equalTo("text1.txt"))
                .body("[1].fileName",equalTo("text2.txt"))
                .log().all();
    }

    @Test(priority = 3)
    void downloadFile(){
        given()
                .when()
                .get("http://localhost:8080/downloadFile/text1.txt")
                .then()
                .statusCode(200)
                .body(contains("1st"))
                .log().all();


    }

}
