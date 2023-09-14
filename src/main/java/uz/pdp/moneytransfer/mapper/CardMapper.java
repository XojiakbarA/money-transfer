package uz.pdp.moneytransfer.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.moneytransfer.dto.CardDTO;
import uz.pdp.moneytransfer.entity.Card;

@Component
public class CardMapper {
    public CardDTO map(Card card) {
        if (card == null) return null;
        CardDTO dto = new CardDTO();
        dto.setId(card.getId());
        dto.setUsername(card.getUsername());
        dto.setNumber(card.getNumber());
        dto.setBalance(card.getBalance());
        dto.setExpireDate(card.getExpireDate());
        dto.setActive(card.getActive());
        return dto;
    }
}
