package uz.pdp.moneytransfer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import uz.pdp.moneytransfer.dto.InputDTO;
import uz.pdp.moneytransfer.dto.UserInputDTO;
import uz.pdp.moneytransfer.entity.Card;
import uz.pdp.moneytransfer.entity.Input;
import uz.pdp.moneytransfer.mapper.InputMapper;
import uz.pdp.moneytransfer.repository.InputRepository;
import uz.pdp.moneytransfer.request.InputRequest;
import uz.pdp.moneytransfer.service.CardService;
import uz.pdp.moneytransfer.service.InputService;

import java.util.List;

@Service
public class InputServiceImpl implements InputService {
    @Autowired
    private InputRepository inputRepository;
    @Autowired
    private CardService cardService;
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

    @Override
    public Page<InputDTO> getAll(Pageable pageable) {
        return inputRepository.findAll(pageable).map(
            i -> inputMapper.mapToInputDTO(i)
        );
    }

    @Override
    public InputDTO getById(Long id) {
        return inputMapper.mapToInputDTO(findById(id));
    }

    @Override
    public InputDTO create(InputRequest request) {
        Input input = new Input();

        setAttributes(input, request);

        return inputMapper.mapToInputDTO(save(input));
    }

    @Override
    public InputDTO update(Long id, InputRequest request) {
        Input input = findById(id);

        setAttributes(input, request);

        return inputMapper.mapToInputDTO(save(input));
    }

    @Override
    public Input findById(Long id) {
        return inputRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(Input.class.getSimpleName() + " not found")
        );
    }

    @Override
    public void deleteById(Long id) {
        if (!inputRepository.existsById(id)) {
            throw new EntityNotFoundException(Input.class.getSimpleName() + " not found");
        }
        inputRepository.deleteById(id);
    }

    private void setAttributes(Input input, InputRequest request) {
        if (request.getFromCardId() != null) {
            Card card = cardService.findById(request.getFromCardId());
            input.setFromCard(card);
        }
        if (request.getToCardId() != null) {
            Card card = cardService.findById(request.getToCardId());
            input.setToCard(card);
        }
        if (request.getAmount() != null) {
            input.setAmount(request.getAmount());
        }
        if (request.getDate() != null) {
            input.setDate(request.getDate());
        }
    }
}
