import org.json.JSONObject;
import org.testng.annotations.Test;

import static Data.Constants.*;
import static Utils.RandomGenerator.getRandomNumber;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredBasic3 {

    @Test
    public void tokenGeneratorTest() {
        baseURI = "https://restful-booker.herokuapp.com";
        String randomNumber = getRandomNumber(1, 2000);

        JSONObject req = new JSONObject();
        req.put("username", "admin");
        req.put("password", "password123");

        String token = given()
                .header("Content-Type", "application/json")
                .body(req.toString())
                .when()
                .post("/auth")
                .then()
                .extract()
                .path("token");

        given().
                header("Content-type", "application/json").
                cookie("token", token).
                when().
                delete("/booking/" + randomNumber + "").
                then().
                statusCode(expectedCreateStatusCode);
    }

    @Test
    public void hamcrestTest() {
        given()
                .when()
                .get("http://ergast.com/api/f1/2017/circuits.json")
                .then()
                .assertThat()
                .body("MRData.CircuitTable.Circuits.circuitId", hasItem("marina_bay"))
                .body("MRData.CircuitTable.Circuits.Location[-1].long", anyOf(greaterThan("1"), equalTo("10")))
                .body("MRData.CircuitTable.Circuits[1,-1].Location.country", contains(countryUSA, countryUAE));
    }


}

