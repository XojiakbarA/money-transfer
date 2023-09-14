package uz.pdp.moneytransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.moneytransfer.entity.Card;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByUsername(String username);
    Optional<Card> findByIdAndUsername(Long id, String username);
    boolean existsByNumber(Long number);
    boolean existsByNumberAndIdNot(Long number, Long id);
}
