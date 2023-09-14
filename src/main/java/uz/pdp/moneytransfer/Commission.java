package uz.pdp.moneytransfer;

import lombok.Getter;

@Getter
public enum Commission {
    FOREIGN_CARD(0.01F), OWN_CARD(0F);

    Commission(Float value) {
        this.value = value;
    }
    private final Float value;
}
