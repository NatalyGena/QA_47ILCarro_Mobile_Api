package rest_tests;

import api_rest.CarController;
import dto.CarDto;
import dto.ErrorMessageDtoString;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class AddNewCarTestsRest extends CarController {

    SoftAssert softAssert = new SoftAssert();


    @Test
    public void addNewCarPositiveTest(){
       int i = new Random() .nextInt(1000)+1000;
        CarDto car =CarDto.builder()
                .serialNumber("12421806"+i)
                .manufacture("Toyota")
                .model("Corolla")
                .year("2024")
                .fuel("Gaz")
                .seats(4)
                .carClass("A")
                .pricePerDay(220.5)
                .city("Holon")
                .build();
        Response response = addNewCar(car);
        softAssert.assertEquals(response.getStatusCode(),200,"Validate status code");
        System.out.println(response.getBody().print());
        softAssert.assertTrue(response.getBody().print()
                .contains("added successfully"),"validate message");
        softAssert.assertAll();
    }
    @Test
    public void addNewCarNegativeTest_WrongAuthorization(){
        int i = new Random() .nextInt(1000)+1000;
        CarDto car =CarDto.builder()
                .serialNumber("12421806"+i)
                .manufacture("Toyota")
                .model("Corolla")
                .year("2024")
                .fuel("Gaz")
                .seats(4)
                .carClass("A")
                .pricePerDay(220.5)
                .city("Holon")
                .build();
        Response response = addNewCarNegative_WrongToken(car,"khgfjhrfty456");
        softAssert.assertEquals(response.getStatusCode(),401,"validate status code");
        System.out.println(response.print());
        softAssert.assertTrue(response.getBody().print()
                .contains("strings must contain exactly 2 "),"validate message");
        softAssert.assertAll();
    }
    @Test
    public void addNewCarNegativeTest_WOSerialNumber(){
        CarDto car =CarDto.builder()
                .manufacture("Toyota")
                .model("Corolla")
                .year("2024")
                .fuel("Gaz")
                .seats(4)
                .carClass("A")
                .pricePerDay(220.5)
                .city("Holon")
                .build();
        Response response = addNewCar(car);
        softAssert.assertEquals(response.getStatusCode(),400,"validate status code");
        System.out.println(response.print());
        softAssert.assertTrue(response.getBody().print()
                .contains("must not be blank"),"validate message");
        ErrorMessageDtoString errorMessageDtoString = response.getBody()
                .as(ErrorMessageDtoString.class);
        softAssert.assertTrue(errorMessageDtoString.getError().equals("Bad Request"),"Validate error");
        softAssert.assertAll();
    }
}
