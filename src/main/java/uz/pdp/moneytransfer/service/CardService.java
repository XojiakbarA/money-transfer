package uz.pdp.moneytransfer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.moneytransfer.dto.CardDTO;
import uz.pdp.moneytransfer.entity.Card;
import uz.pdp.moneytransfer.request.CardRequest;

import java.util.List;

public interface CardService {
    Page<CardDTO> getAll(Pageable pageable);
    List<CardDTO> getAllByUsername(String username);
    CardDTO getByIdAndUsername(Long id, String username);
    CardDTO getById(Long id);
    CardDTO create(CardRequest request);
    CardDTO update(Long id, CardRequest request);
    void deleteById(Long id);
    Card findByIdAndUsername(Long id, String username);
    Card findById(Long id);
    Card save(Card card);
    void activate(Long id);
    void deactivate(Long id);
    void activateByIdAndUsername(Long id, String username);
    void deactivateByIdAndUsername(Long id, String username);
}
