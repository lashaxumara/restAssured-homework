import Steps.XMLStep;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static Data.Constants.*;

public class RestAssuredXML {
    @Test
    public void xmlPaths() {
        Assert.assertEquals( XMLStep.count(),exceptedSize);
        Assert.assertTrue(XMLStep.sNameList() != null);
        Assert.assertEquals(XMLStep.sName(),countryAn);
        Assert.assertEquals(XMLStep.lastName(),countryAmerica);
    }

    @Test
    public void findPerson() throws IOException {
        String[] result = XMLStep.findPersonInfo();
        String homeStreet = result[0];
        String officeZip = result[1];
        Assert.assertEquals(homeStreet, exceptedHomeStreet);
        Assert.assertEquals(officeZip, exceptedOfficeZip);

    }

}



