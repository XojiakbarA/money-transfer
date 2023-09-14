package uz.pdp.moneytransfer.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.moneytransfer.dto.UserInputDTO;
import uz.pdp.moneytransfer.entity.Input;

@Component
public class InputMapper {
    public UserInputDTO map(Input input) {
        if (input == null) return null;
        UserInputDTO dto = new UserInputDTO();
        dto.setId(input.getId());
        dto.setUsername(input.getFromCard().getUsername());
        dto.setNumber(input.getFromCard().getNumber());
        dto.setAmount(input.getAmount());
        dto.setDate(input.getDate());
        return dto;
    }
}
