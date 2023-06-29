package api.data.model.requestModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Data
@JsonIgnoreProperties({"saleprice"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookRequest {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookCheckRequest bookingdates;
    private String additionalneeds;
    private int saleprice;
    private Integer passportNO;
}
