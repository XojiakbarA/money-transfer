package uz.pdp.moneytransfer.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransferRequest {
    @NotNull(message = "fromCardId must not be null")
    @Positive(message = "fromCardId must be a positive")
    private Long fromCardId;

    @NotNull(message = "toCardId must not be null")
    @Positive(message = "toCardId must be a positive")
    private Long toCardId;

    @NotNull(message = "amount must not be null")
    @Positive(message = "amount must be a positive")
    @Min(value = 1000, message = "min value of amount must be 1000")
    private Float amount;
}
