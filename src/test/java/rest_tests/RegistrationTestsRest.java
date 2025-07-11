package rest_tests;

import api_rest.AuthenticationController;
import dto.ErrorMessageDtoString;
import dto.RegistrationBodyDTo;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class RegistrationTestsRest  extends AuthenticationController {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000);
        RegistrationBodyDTo user = RegistrationBodyDTo.builder()
                .username("nataly_gena" + i + "@gmail.com")
                .password("ASD22zxc!")
                .firstName("Nataly")
                .lastName("Gena")
                .build();
        Assert.assertEquals(registrationLogin(user, REGISTRATION_URL)
                .getStatusCode(), 200);

    }

    @Test
    public void registrationNegativeTest_WrongEmail() {
        int i = new Random().nextInt(1000);
        RegistrationBodyDTo user = RegistrationBodyDTo.builder()
                .username("nataly_gena" + i + "gmail.com") //1.@gmail.com 2.ggffyo@e;3.ppp@.com;4.terhyer@@ruy.ghj;5.kju@hg.;
                .password("ASD22zxc!")                 //6.qwerty @gmail.com;7.qwert@שדש.com
                .firstName("Nataly")
                .lastName("Gena")
                .build();
        Response response = registrationLogin(user, REGISTRATION_URL);
        softAssert.assertEquals(response.getStatusCode(), 400, "validate status code");
        ErrorMessageDtoString errorMessageDtoString = response.getBody().as(ErrorMessageDtoString.class);
        softAssert.assertEquals(errorMessageDtoString.getError(),
                "Bad Request", "validate error");
        System.out.println(errorMessageDtoString);
        softAssert.assertTrue(errorMessageDtoString.getMessage()
                .toString().contains("must be a well-formed"), "validate message");
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_WrongPassword() {
        int i = new Random().nextInt(1000);
        RegistrationBodyDTo user = RegistrationBodyDTo.builder()
                .username("nataly_gena" + i + "@gmail.com")
                .password("ASD22zxc") //1.aaaa432!;2.Asssfgh*;4.LKJGLK2@;5.ידשךג23&;
                .firstName("Nataly")
                .lastName("Gena")
                .build();
        Response response = registrationLogin(user, REGISTRATION_URL);
        softAssert.assertEquals(response.getStatusCode(), 400, "validate status code");
        ErrorMessageDtoString errorMessageDtoString = response.getBody().as(ErrorMessageDtoString.class);
        softAssert.assertEquals(errorMessageDtoString.getError(),
                "Bad Request", "validate error");
        System.out.println(errorMessageDtoString);
        softAssert.assertTrue(errorMessageDtoString.getMessage()
                .toString().contains("Can contain special characters [@$#^&*!]"), "validate message");
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_EmptyFirstName() {
        int i = new Random().nextInt(1000);
        RegistrationBodyDTo user = RegistrationBodyDTo.builder()
                .username("nataly_gena" + i + "@gmail.com")
                .password("ASD22zxc!")
                .firstName("")
                .lastName("Gena")
                .build();
        Response response = registrationLogin(user, REGISTRATION_URL);
        softAssert.assertEquals(response.getStatusCode(), 400, "validate status code");
        ErrorMessageDtoString errorMessageDtoString = response.getBody().as(ErrorMessageDtoString.class);
        softAssert.assertEquals(errorMessageDtoString.getError(),
                "Bad Request", "validate error");
        System.out.println(errorMessageDtoString);
        softAssert.assertTrue(errorMessageDtoString.getMessage()
                .toString().contains("not be blank"), "validate message");
        softAssert.assertAll();
    }
}