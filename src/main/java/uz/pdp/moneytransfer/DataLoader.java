package uz.pdp.moneytransfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.moneytransfer.entity.Card;
import uz.pdp.moneytransfer.service.CardService;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private CardService cardService;

    @Override
    public void run(String... args) throws Exception {
        createCards();
    }

    private void createCards() {
        Card card1 = new Card();
        card1.setUsername("user1");
        card1.setNumber(1111_1111_1111_1111L);
        card1.setBalance(5_000_000F);
        cardService.save(card1);

        Card card2 = new Card();
        card2.setUsername("user1");
        card2.setNumber(2222_2222_2222_2222L);
        card2.setBalance(1_000_000F);
        cardService.save(card2);

        Card card3 = new Card();
        card3.setUsername("user2");
        card3.setNumber(3333_3333_3333_3333L);
        card3.setBalance(3_000_000F);
        cardService.save(card3);

        Card card4 = new Card();
        card4.setUsername("user2");
        card4.setNumber(4444_4444_4444_4444L);
        card4.setBalance(2_500_000F);
        cardService.save(card4);

        Card card5 = new Card();
        card5.setUsername("user3");
        card5.setNumber(5555_5555_5555_5555L);
        card5.setBalance(1_500_000F);
        cardService.save(card5);
    }
}
