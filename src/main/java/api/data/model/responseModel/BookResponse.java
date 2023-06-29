package api.data.model.responseModel;

import api.data.model.requestModel.BookCheckRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@JsonPropertyOrder({"lastname","firstname"})
public class BookResponse {
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("totalprice")
    private int totalPrice;
    @JsonProperty("depositpaid")
    private boolean depositPaid;
    private BookCheckRequest bookingdates;
    @JsonProperty("additionalneeds")
    private String additionalNeeds;
}
