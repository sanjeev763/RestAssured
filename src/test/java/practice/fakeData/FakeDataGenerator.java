package practice.fakeData;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class FakeDataGenerator {
    @Test
    void testDummyData(){
        Faker faker = new Faker();
        String fullname = faker.name().fullName();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String colour = faker.color().name();
        String email = faker.internet().safeEmailAddress();
        String password = faker.internet().password();
        String phone = faker.phoneNumber().cellPhone();

        System.out.println("fullname :" +fullname);
        System.out.println("firstname :" +firstname);
        System.out.println("lastname :" +lastname);
        System.out.println("colour :" +colour);
        System.out.println("email :" +email);
        System.out.println("password :" +password);
        System.out.println("phone :" +phone);
    }
}
