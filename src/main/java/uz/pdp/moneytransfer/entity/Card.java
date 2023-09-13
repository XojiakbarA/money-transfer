package uz.pdp.moneytransfer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private Long number;

    @Column(nullable = false)
    private Float balance;

    @Column(nullable = false)
    private Date expireDate;

    @Column(nullable = false)
    private Boolean active = true;
}
