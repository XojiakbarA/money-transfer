package uz.pdp.moneytransfer.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private String message;
    private Map<String, String> errors;
}
