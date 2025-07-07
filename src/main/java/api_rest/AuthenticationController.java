package api_rest;

import dto.RegistrationBodyDTo;
import interfaces.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class AuthenticationController implements BaseApi {

    public Response registrationLogin(RegistrationBodyDTo user,String url){
        return given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + url)
                .thenReturn();


    }
}
