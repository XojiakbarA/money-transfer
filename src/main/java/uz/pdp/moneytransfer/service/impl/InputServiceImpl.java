package uz.pdp.moneytransfer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.moneytransfer.dto.UserInputDTO;
import uz.pdp.moneytransfer.entity.Input;
import uz.pdp.moneytransfer.mapper.InputMapper;
import uz.pdp.moneytransfer.repository.InputRepository;
import uz.pdp.moneytransfer.service.InputService;

import java.util.List;

@Service
public class InputServiceImpl implements InputService {
    @Autowired
    private InputRepository inputRepository;
    @Autowired
    private InputMapper inputMapper;

    @Override
    public List<UserInputDTO> findAllByToCard_Username(String username) {
        return inputRepository.findAllByToCard_Username(username).stream().map(
                input -> inputMapper.map(input)
        ).toList();
    }

    @Override
    public Input save(Input input) {
        return inputRepository.save(input);
    }
}
