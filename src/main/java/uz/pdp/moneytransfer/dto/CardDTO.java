package uz.pdp.moneytransfer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CardDTO {
    private Long id;
    private String username;
    private Long number;
    private Float balance;
    private Date expireDate;
    private Boolean active;
}
