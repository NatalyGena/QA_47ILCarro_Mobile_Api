package rest_tests;


import api_rest.CarController;
import dto.CarsDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetUserCarsTestsRest extends CarController {
SoftAssert softAssert = new SoftAssert();
CarsDto cars;

@Test
    public void getUserCarPositiveTest(){
    Response response = getUserCars();
    cars =response.getBody().as(CarsDto.class);
    System.out.println(cars);
    softAssert.assertEquals(response.getStatusCode(),200,"validate status code");
    softAssert.assertTrue(cars.getCars()[0].getManufacture().contains("Toyota"),"validate manufacture");
    softAssert.assertAll();
}
@Test
    public void getUserCarsNegativeTest_WrongUrl(){
    Response response = getUserCarsNegative_wrongUrl(REGISTRATION_URL);
    System.out.println(response.getStatusCode());
    Assert.assertEquals(response.getStatusCode(),403);


}

}
