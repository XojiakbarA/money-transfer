package uz.pdp.moneytransfer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.moneytransfer.dto.UserOutputDTO;
import uz.pdp.moneytransfer.entity.Output;
import uz.pdp.moneytransfer.mapper.OutputMapper;
import uz.pdp.moneytransfer.repository.OutputRepository;
import uz.pdp.moneytransfer.service.OutputService;

import java.util.List;

@Service
public class OutputServiceImpl implements OutputService {
    @Autowired
    private OutputRepository outputRepository;
    @Autowired
    private OutputMapper outputMapper;

    @Override
    public List<UserOutputDTO> findAllByFromCard_Username(String username) {
        return outputRepository.findAllByFromCard_Username(username).stream().map(
                o -> outputMapper.map(o)
        ).toList();
    }

    @Override
    public Output save(Output output) {
        return outputRepository.save(output);
    }
}
