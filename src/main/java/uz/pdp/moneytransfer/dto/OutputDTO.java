package uz.pdp.moneytransfer.dto;

import java.util.Date;

import lombok.Data;

@Data
public class OutputDTO {
    private Long id;
    private CardDTO fromCard;
    private CardDTO toCard;
    private Float amount;
    private Date date;
    private Float commissionAmount;
}
