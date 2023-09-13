package uz.pdp.moneytransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.moneytransfer.entity.Input;

import java.util.List;

@Repository
public interface InputRepository extends JpaRepository<Input, Long> {
    List<Input> findAllByToCard_Username(String username);
}
