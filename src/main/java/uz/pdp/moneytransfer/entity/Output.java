package uz.pdp.moneytransfer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "outputs")
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Card fromCard;

    @ManyToOne
    private Card toCard;

    @Column(nullable = false)
    private Float amount;

    @Column(nullable = false)
    private Date date = new Date();

    @Column(nullable = false)
    private Float commissionAmount;
}
