
import api.data.DataP;
import api.data.model.requestModel.BookCheckRequest;
import api.data.model.requestModel.BookRequest;
import api.data.model.responseModel.BookResponse;
import api.steps.BookingStep;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static api.data.Constants.statusCode;

public class RestAssured6 {
    BookingStep bookingStep = new BookingStep();
    BookRequest bookRequest = new BookRequest();
    BookCheckRequest bookCheckRequest = new BookCheckRequest();

    @Test(dataProvider = "dp", dataProviderClass = DataP.class)
    public void dpUpdatingTest(String fName, String lName, int totalPrice, boolean depositPaid, String checkIn, String checkOut, String additionalNeeds, int salePrice, Integer passportNo) {
        bookRequest.setFirstname(fName);
        bookRequest.setLastname(lName);
        bookRequest.setTotalprice(totalPrice);
        bookRequest.setDepositpaid(depositPaid);
        bookCheckRequest.setCheckin(checkIn);
        bookCheckRequest.setCheckout(checkOut);
        bookRequest.setBookingdates(bookCheckRequest);
        bookRequest.setAdditionalneeds(additionalNeeds);
        bookRequest.setSaleprice(salePrice);
        bookRequest.setPassportNO(passportNo);

        Response response = bookingStep.bookingUpdate(bookRequest);
        int statusCodeApi = bookingStep.getStatusCode(response);
        BookResponse bookResponse = bookingStep.deserialize(response);

        Assert.assertEquals(statusCodeApi, statusCode);
        Assert.assertEquals(bookResponse.getFirstName(), fName);
        Assert.assertEquals(bookResponse.getLastName(), lName);
    }

}
