package uz.pdp.moneytransfer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import uz.pdp.moneytransfer.dto.OutputDTO;
import uz.pdp.moneytransfer.dto.UserOutputDTO;
import uz.pdp.moneytransfer.entity.Card;
import uz.pdp.moneytransfer.entity.Output;
import uz.pdp.moneytransfer.mapper.OutputMapper;
import uz.pdp.moneytransfer.repository.OutputRepository;
import uz.pdp.moneytransfer.request.OutputRequest;
import uz.pdp.moneytransfer.service.CardService;
import uz.pdp.moneytransfer.service.OutputService;

import java.util.List;

@Service
public class OutputServiceImpl implements OutputService {
    @Autowired
    private OutputRepository outputRepository;
    @Autowired
    private CardService cardService;
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

    @Override
    public Page<OutputDTO> getAll(Pageable pageable) {
        return outputRepository.findAll(pageable).map(
            o -> outputMapper.mapToOutputDTO(o)
        );
    }

    @Override
    public OutputDTO getById(Long id) {
        return outputMapper.mapToOutputDTO(findById(id));
    }

    @Override
    public OutputDTO create(OutputRequest request) {
        Output output = new Output();

        setAttributes(output, request);

        return outputMapper.mapToOutputDTO(save(output));
    }

    @Override
    public OutputDTO update(Long id, OutputRequest request) {
        Output output = findById(id);

        setAttributes(output, request);

        return outputMapper.mapToOutputDTO(save(output));
    }

    @Override
    public Output findById(Long id) {
        return outputRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(Output.class.getSimpleName() + " not found")
        );
    }

    @Override
    public void deleteById(Long id) {
        if (!outputRepository.existsById(id)) {
            throw new EntityNotFoundException(Output.class.getSimpleName() + " not found");
        }
        outputRepository.deleteById(id);
    }

    private void setAttributes(Output output, OutputRequest request) {
        if (request.getFromCardId() != null) {
            Card card = cardService.findById(request.getFromCardId());
            output.setFromCard(card);
        }
        if (request.getToCardId() != null) {
            Card card = cardService.findById(request.getToCardId());
            output.setToCard(card);
        }
        if (request.getAmount() != null) {
            output.setAmount(request.getAmount());
        }
        if (request.getDate() != null) {
            output.setDate(request.getDate());
        }
        if (request.getCommissionAmount() != null) {
            output.setCommissionAmount(request.getCommissionAmount());
        }
    }
}
