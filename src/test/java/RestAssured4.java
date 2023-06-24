import Deserialize.JSONSuccessResponse;
import Deserialize.JSONUnsuccessResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Data.Constants.*;
import static Steps.Responses.getResponse;
import static Steps.Responses.getUserResponse;

public class RestAssured4 {

    @Test
    public void checkSuccessScenario() {
        JSONSuccessResponse jsonSuccessResponse;
        Response response = getResponse(successfulEmail, successfulPassword);

        if (response.statusCode() == exceptedGetStatusCode) {
            jsonSuccessResponse = response.getBody().as(JSONSuccessResponse.class);
            Assert.assertEquals(jsonSuccessResponse.id, successResponseID);
            Assert.assertEquals(jsonSuccessResponse.token, successResponseToken);
        }
    }

    @Test
    public void checkUnsuccessScenario() {
        JSONUnsuccessResponse jsonUnsuccessResponse;
        Response response = getResponse(unsuccessfulEmail, "");

        if (response.statusCode() == exceptedErrorStatusCode) {
            jsonUnsuccessResponse = response.getBody().as(JSONUnsuccessResponse.class);
            Assert.assertEquals(jsonUnsuccessResponse.error, unSuccessResponse);
        }
    }

    @Test
    public void userTest() {
        int statusCode = getUserResponse(usersName, usersJob);
        Assert.assertEquals(statusCode, expectedCreateStatusCode);
    }
}



