package api.data.model.requestModel;

import lombok.*;

@Data
public class BookCheckRequest {
    private String checkin;
    private String checkout;
}
