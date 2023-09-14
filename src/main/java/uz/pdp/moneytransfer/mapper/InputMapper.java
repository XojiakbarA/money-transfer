package uz.pdp.moneytransfer.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uz.pdp.moneytransfer.dto.InputDTO;
import uz.pdp.moneytransfer.dto.UserInputDTO;
import uz.pdp.moneytransfer.entity.Input;

@Component
public class InputMapper {
    @Autowired
    private CardMapper cardMapper;

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
    public InputDTO mapToInputDTO(Input input) {
        if (input == null) return null;
        InputDTO dto = new InputDTO();
        dto.setId(input.getId());
        dto.setFromCard(cardMapper.map(input.getFromCard()));
        dto.setToCard(cardMapper.map(input.getToCard()));
        dto.setAmount(input.getAmount());
        dto.setDate(input.getDate());
        return dto;
    }
}
