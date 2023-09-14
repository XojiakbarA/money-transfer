package uz.pdp.moneytransfer.service;

import uz.pdp.moneytransfer.dto.InputDTO;
import uz.pdp.moneytransfer.dto.UserInputDTO;
import uz.pdp.moneytransfer.entity.Input;
import uz.pdp.moneytransfer.request.InputRequest;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InputService {
    Page<InputDTO> getAll(Pageable pageable);
    InputDTO getById(Long id);
    InputDTO create(InputRequest request);
    InputDTO update(Long id, InputRequest request);
    List<UserInputDTO> findAllByToCard_Username(String username);
    Input findById(Long id);
    Input save(Input input);
    void deleteById(Long id);
}
