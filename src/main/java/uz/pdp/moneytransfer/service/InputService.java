package uz.pdp.moneytransfer.service;

import uz.pdp.moneytransfer.dto.UserInputDTO;
import uz.pdp.moneytransfer.entity.Input;

import java.util.List;

public interface InputService {
    List<UserInputDTO> findAllByToCard_Username(String username);
    Input save(Input input);
}
