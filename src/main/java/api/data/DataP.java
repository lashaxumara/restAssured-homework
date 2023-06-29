package api.data;

import org.testng.annotations.DataProvider;

import static api.data.Constants.*;

public class DataP {
    @DataProvider(name = "dp")
    public Object[][] provideBookResponseData() {
        return new Object[][]{
                {name, lastName, totalPrice, depositPaid, checkIn, checkOut, additionalNeeds, salePrice, null},
                {"Jane", "Smith", 200, false, "2020-02-02", "2023-04-04", "breakfast", 100, null}
        };
    }
}
