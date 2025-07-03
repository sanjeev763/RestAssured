package practice.serializationAndDeserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

//POJO --> JSON : Serialization
//JSON --> POJO : Deserialization
public class SerializationAndDeserialization {
    @Test(priority=1)
    void convertPOJOToJSON() throws JsonProcessingException {
        //create java object using pojo class
        Student student = new Student(); //At this point data is in the form of pojo
        student.setName("sanjeev");
        student.setLocation("India");
        student.setPhone("123456");
        String[] courses = {"c", "c++"};
        student.setCoursesArr(courses);
        //convert java object to json
        ObjectMapper objectMapper = new ObjectMapper();
        String jsondata = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
        System.out.println(jsondata);
    }

    @Test(priority=2)
    void convertJSONToPOJO() throws JsonProcessingException {
        String jsonData = "{\n" +
                "  \"name\" : \"sanjeev\",\n" +
                "  \"location\" : \"India\",\n" +
                "  \"phone\" : \"123456\",\n" +
                "  \"coursesArr\" : [ \"c\", \"c++\" ]\n" +
                "}";
        //convert json to java object
        ObjectMapper objectMapper = new ObjectMapper();
        Student std = objectMapper.readValue(jsonData, Student.class);
        System.out.println(std.getLocation());
        System.out.println(std.getName());
        System.out.println(std.getPhone());
        System.out.println(std.getCoursesArr()[0]);
        System.out.println(std.getCoursesArr()[1]);
    }
}
