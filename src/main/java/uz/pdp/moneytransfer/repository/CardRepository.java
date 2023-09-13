package uz.pdp.moneytransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.moneytransfer.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
