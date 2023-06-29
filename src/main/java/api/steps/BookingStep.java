package api.steps;

import api.data.model.requestModel.BookRequest;
import api.data.model.responseModel.BookResponse;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

import static api.data.Constants.*;
import static io.restassured.RestAssured.given;


public class BookingStep {
    @Step
    public Response bookingUpdate(BookRequest bookRequest) {
        Response response = given()
                .filter(new AllureRestAssured())
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + token)
                .pathParam("id", bookingId)
                .body(bookRequest)
                .when()
                .put(BOOKING_ENDPOINT);

        return response;
    }

    @Step
    public int getStatusCode(Response response){
        return response.getStatusCode();
    }

    @Step
    public BookResponse deserialize(Response response){
        return response.getBody().as(BookResponse.class);
    }

}
