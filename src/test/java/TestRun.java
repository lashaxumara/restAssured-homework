import Data.Constants;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestRun extends Constants {

    @BeforeMethod
    public void specialSetup() {
        RestAssured.baseURI = "http://ergast.com/api/f1";
    }

    @Test(dataProvider = "provider")
    public void circuitRun(int index, String countryDp) {
        Response response = RestAssured.get("2017/circuits.json");
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);

        //get id from first link
        String circuitId1 = jsonPath.getString("MRData.CircuitTable.Circuits["+index+"].circuitId");

        //call second link
        Response circuitResponse1 = RestAssured.get("circuits/" + circuitId1 + ".json");
        String circuitResponseBody1 = circuitResponse1.getBody().asString();
        JsonPath jsonPath1 = new JsonPath(circuitResponseBody1);
        String country = jsonPath1.getString("MRData.CircuitTable.Circuits[0].Location.country");
        Assert.assertEquals(country,countryDp);

    }

    @DataProvider(name = "provider")
    public Object[][] dpMethod(){
        return new Object[][] {{index1, countryUSA}, {index5, countryHun}};
    }
}
