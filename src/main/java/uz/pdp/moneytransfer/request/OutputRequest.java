package uz.pdp.moneytransfer.request;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import uz.pdp.moneytransfer.marker.OnCreate;

@Data
public class OutputRequest {
    @NotNull(message = "fromCardId must not be null", groups = OnCreate.class)
    @Positive(message = "fromCardId must be a positive")
    private Long fromCardId;

    @NotNull(message = "toCardId must not be null", groups = OnCreate.class)
    @Positive(message = "toCardId must be a positive")
    private Long toCardId;

    @NotNull(message = "amount must not be null", groups = OnCreate.class)
    @Positive(message = "amount must be a positive")
    private Float amount;
    
    private Date date;

    @NotNull(message = "commissionAmount must not be null", groups = OnCreate.class)
    @Positive(message = "commissionAmount must be a positive")
    private Float commissionAmount;
}
