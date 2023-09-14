package uz.pdp.moneytransfer.service;

import uz.pdp.moneytransfer.dto.OutputDTO;
import uz.pdp.moneytransfer.dto.UserOutputDTO;
import uz.pdp.moneytransfer.entity.Output;
import uz.pdp.moneytransfer.request.OutputRequest;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OutputService {
    Page<OutputDTO> getAll(Pageable pageable);
    OutputDTO getById(Long id);
    OutputDTO create(OutputRequest request);
    OutputDTO update(Long id, OutputRequest request);
    List<UserOutputDTO> findAllByFromCard_Username(String username);
    Output findById(Long id);
    Output save(Output output);
    void deleteById(Long id);
}
