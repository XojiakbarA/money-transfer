package uz.pdp.moneytransfer.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import uz.pdp.moneytransfer.marker.OnCreate;
import uz.pdp.moneytransfer.validator.IsFuture;

import java.util.Date;

@Data
public class CardRequest {
    @NotNull(message = "username must not be null", groups = OnCreate.class)
    @NotBlank(message = "username must not be empty")
    private String username;

    @NotNull(message = "number must not be null", groups = OnCreate.class)
    @Positive(message = "number must be a positive")
    @Min(value = 1000_0000_0000_0000L, message = "number must be 16 digits")
    @Max(value = 9999_9999_9999_9999L, message = "number must be 16 digits")
    private Long number;

    @Positive(message = "balance must be a positive")
    private Float balance;

    @IsFuture(message = "expireDate must be future")
    private Date expireDate;
}
