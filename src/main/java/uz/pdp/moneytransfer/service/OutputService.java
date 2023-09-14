package uz.pdp.moneytransfer.service;

import uz.pdp.moneytransfer.dto.UserOutputDTO;
import uz.pdp.moneytransfer.entity.Output;

import java.util.List;

public interface OutputService {
    List<UserOutputDTO> findAllByFromCard_Username(String username);
    Output save(Output output);
}
