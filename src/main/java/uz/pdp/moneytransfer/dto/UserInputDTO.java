package uz.pdp.moneytransfer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserInputDTO {
    private Long id;
    private String username;
    private Long number;
    private Float amount;
    private Date date;
}
