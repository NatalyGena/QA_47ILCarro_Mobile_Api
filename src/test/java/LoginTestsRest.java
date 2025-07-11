import api_rest.AuthenticationController;
import dto.RegistrationBodyDTo;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class LoginTestsRest extends AuthenticationController {
    RegistrationBodyDTo user;
    @BeforeMethod
    public void registrationUser(){
        int i = new Random().nextInt(1000);
         user = RegistrationBodyDTo.builder()
                .username("nataly_gena" + i + "@gmail.com")
                .password("ASD22zxc!")
                .firstName("Nataly")
                .lastName("Gena")
                .build();
        System.out.println("Registration result--->>> "
                +registrationLogin(user,REGISTRATION_URL).getStatusCode());
        System.out.println(user);
    }
    @Test
    public void loginPositiveTest(){
        Assert.assertEquals(registrationLogin(user,LOGIN_URL).getStatusCode(),200);

    }
    @Test
    public void loginNegativeTest_WrongEmail_UnregisteredUser(){
         user.setUsername("nataly2gena2@gmail.com");
        Response response = registrationLogin(user,LOGIN_URL);
        System.out.println(response.getBody().print());
        Assert.assertEquals(response.getStatusCode(),401);
    }
}
