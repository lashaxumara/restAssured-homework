package Steps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class XMLStep {
    @Step("count all 'sName' node")
    public static int count() {
        Response response = RestAssured.get("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName");
        XmlPath xmlPath = response.xmlPath();
        int count = xmlPath.getList("ArrayOftContinent.tContinent.sName").size();
        return count;
    }

    @Step("get list of all 'sName' node's value")
    public static List<String> sNameList() {
        Response response = RestAssured.get("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName");
        XmlPath xmlPath = response.xmlPath();
        List<String> sNameList = xmlPath.getList("ArrayOftContinent.tContinent.sName");
        for (String sName : sNameList) {
            System.out.println("sName: " + sName);
        }
        return sNameList;
    }

    @Step("get 'sName' node result with value of sCode equals to 'AN'")
    public static String sName() {
        Response response = RestAssured.get("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName");
        XmlPath xmlPath = response.xmlPath();
        String sName = xmlPath.getString("ArrayOftContinent.tContinent.find { it.sCode == 'AN' }.sName");
        return sName;
    }

    @Step("get the last tContinent node's sName value")
    public static String lastName() {
        Response response = RestAssured.get("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName");
        XmlPath xmlPath = response.xmlPath();
        String lastSName = xmlPath.getString("ArrayOftContinent.tContinent[-1].sName");
        return lastSName;
    }

    @Step("get Home street and Office zip values using xmlPath ")
    public static String[] findPersonInfo() throws IOException {
        RestAssured.baseURI = "https://www.crcind.com/csp/samples/SOAP.Demo.CLS?WSDL=1";
        String xmlBody = new String(Files.readAllBytes(Paths.get("SOAP.xml")));

        Response response = RestAssured.given()
                .contentType("application/soap+xml")
                .body(xmlBody)
                .post();

        XmlPath xmlPath = response.xmlPath();
        String homeStreet = xmlPath.getString("Envelope.Body.FindPersonResponse.FindPersonResult.Home.Street");
        String officeZip = xmlPath.getString("Envelope.Body.FindPersonResponse.FindPersonResult.Office.Zip");
        return new String[]{homeStreet, officeZip};
    }


}
