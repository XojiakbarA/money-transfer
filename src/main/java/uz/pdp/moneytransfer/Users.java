package uz.pdp.moneytransfer;

import lombok.Getter;

@Getter
public enum Users {
    USER1("user1"), USER2("user2"), USER3("user3");

    Users(String username) {
        this.username = username;
    }

    private final String username;
}
