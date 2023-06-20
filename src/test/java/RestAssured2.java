import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static Data.Constants.expectedStatusCode;
import static Data.Constants.lastBookIsbn;

public class RestAssured2 {

    @Test
    public void log() {
        JSONObject bookingBody = new JSONObject();
        bookingBody.put("firstname", "James");
        bookingBody.put("lastname", "Brown");
        bookingBody.put("totalprice", 111);
        bookingBody.put("depositpaid", true);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        bookingBody.put("bookingdates", bookingDates);
        bookingBody.put("additionalneeds", "Breakfast");

        RequestSpecification request = RestAssured.given();
        int statusCode = request
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body(bookingBody.toString())
                .contentType(ContentType.JSON)
                .put("https://restful-booker.herokuapp.com/booking/1")
                .then()
                .log().ifStatusCodeIsEqualTo(expectedStatusCode)
                .extract()
                .statusCode();

        System.out.println(statusCode);
    }

    @Test
    public void books() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com/BookStore";
        RequestSpecification request = RestAssured.given();
        JsonPath jsonPath = request
                .when()
                .get("/v1/Books")
                .then()
                .extract()
                .jsonPath();

        String lastIsbn = jsonPath.getString("books[-1].isbn");
        List<String> publishDates = jsonPath.getList("books.publish_date");

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        for (String publishDate : publishDates) {
            LocalDateTime publishDateTime = LocalDateTime.parse(publishDate, formatter);
            Assert.assertTrue(publishDateTime.isBefore(currentTime));
        }
        Assert.assertEquals(lastIsbn, lastBookIsbn);
    }
}




