package Steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class Responses {
    public static Response getResponse(String email, String password){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email);
        jsonObject.put("password", password);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .post("https://reqres.in/api/register")
                .then()
                .extract()
                .response();

        return response;
    }

    public static int getUserResponse(String name, String job){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("job", job);

        int statusCode = given()
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .post("https://reqres.in/api/users")
                .getStatusCode();

        return statusCode;
    }
}
