package uz.pdp.moneytransfer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserOutputDTO {
    private Long id;
    private String username;
    private Long number;
    private Float amount;
    private Date date;
    private Float commissionAmount;
}
