package uz.pdp.moneytransfer.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.moneytransfer.dto.CardDTO;
import uz.pdp.moneytransfer.entity.Card;
import uz.pdp.moneytransfer.mapper.CardMapper;
import uz.pdp.moneytransfer.repository.CardRepository;
import uz.pdp.moneytransfer.request.CardRequest;
import uz.pdp.moneytransfer.service.CardService;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public Page<CardDTO> getAll(Pageable pageable) {
        return cardRepository.findAll(pageable).map(c -> cardMapper.map(c));
    }

    @Override
    public List<CardDTO> getAllByUsername(String username) {
        return cardRepository.findAllByUsername(username).stream().map(
                c -> cardMapper.map(c)
        ).toList();
    }

    @Override
    public CardDTO getByIdAndUsername(Long id, String username) {
        return cardMapper.map(findByIdAndUsername(id, username););
    }

    @Override
    public CardDTO getById(Long id) {
        return cardMapper.map(findById(id));
    }

    @Override
    public CardDTO create(CardRequest request) {
        Card card = new Card();

        setUsername(card, request);

        setNumber(card, request);

        setAttributes(card, request);

        return cardMapper.map(save(card));
    }

    @Override
    public CardDTO update(Long id, CardRequest request) {
        Card card = findById(id);

        setUsername(card, request);

        setNumber(card, request, id);

        setAttributes(card, request);

        return cardMapper.map(save(card));
    }

    @Override
    public void deleteById(Long id) {
        if (!cardRepository.existsById(id)) {
            throw new EntityNotFoundException(Card.class.getSimpleName() + " not found");
        }
        cardRepository.deleteById(id);
    }

    @Override
    public Card findByIdAndUsername(Long id, String username) {
        return cardRepository.findByIdAndUsername(id, username).orElseThrow(
                () -> new EntityNotFoundException(Card.class.getSimpleName() + " not found")
        );
    }

    @Override
    public Card findById(Long id) {
        return cardRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Card.class.getSimpleName() + " not found")
        );
    }

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public void activateByIdAndUsername(Long id, String username) {
        Card card = findByIdAndUsername(id, username);
        card.setActive(true);
        save(card);
    }

    @Override
    public void deactivateByIdAndUsername(Long id, String username) {
        Card card = findByIdAndUsername(id, username);
        card.setActive(false);
        save(card);
    }

    @Override
    public void activate(Long id) {
        Card card = findById(id);
        card.setActive(true);
        save(card);
    }

    @Override
    public void deactivate(Long id) {
        Card card = findById(id);
        card.setActive(false);
        save(card);
    }

    private void setUsername(Card card, CardRequest request) {
        if (request.getUsername() != null && !request.getUsername().isBlank()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            if (userDetails == null) {
                throw new UsernameNotFoundException("User not found");
            }
            card.setUsername(request.getUsername());
        }
    }
    private void setNumber(Card card, CardRequest request) {
        if (request.getNumber() != null) {
            if (cardRepository.existsByNumber(request.getNumber())) {
                throw new EntityExistsException(Card.class.getSimpleName() + " already exists");
            }
            card.setNumber(request.getNumber());
        }
    }
    private void setNumber(Card card, CardRequest request, Long id) {
        if (request.getNumber() != null) {
            if (cardRepository.existsByNumberAndIdNot(request.getNumber(), id)) {
                throw new EntityExistsException(Card.class.getSimpleName() + " already exists");
            }
            card.setNumber(request.getNumber());
        }
    }
    private void setAttributes(Card card, CardRequest request) {
        if (request.getBalance() != null) {
            card.setBalance(request.getBalance());
        }
        if (request.getExpireDate() != null) {
            card.setExpireDate(request.getExpireDate());
        }
    }
}
