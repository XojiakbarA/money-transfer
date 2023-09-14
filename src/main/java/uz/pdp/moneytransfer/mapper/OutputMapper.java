package uz.pdp.moneytransfer.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uz.pdp.moneytransfer.dto.OutputDTO;
import uz.pdp.moneytransfer.dto.UserOutputDTO;
import uz.pdp.moneytransfer.entity.Output;

@Component
public class OutputMapper {
    @Autowired
    private CardMapper cardMapper;

    public UserOutputDTO map(Output output) {
        if (output == null) return null;
        UserOutputDTO dto = new UserOutputDTO();
        dto.setId(output.getId());
        dto.setUsername(output.getToCard().getUsername());
        dto.setNumber(output.getToCard().getNumber());
        dto.setAmount(output.getAmount());
        dto.setDate(output.getDate());
        dto.setCommissionAmount(output.getCommissionAmount());
        return dto;
    }
    public OutputDTO mapToOutputDTO(Output output) {
        if (output == null) return null;
        OutputDTO dto = new OutputDTO();
        dto.setId(output.getId());
        dto.setFromCard(cardMapper.map(output.getFromCard()));
        dto.setToCard(cardMapper.map(output.getToCard()));
        dto.setAmount(output.getAmount());
        dto.setDate(output.getDate());
        dto.setCommissionAmount(output.getCommissionAmount());
        return dto;
    }
}
