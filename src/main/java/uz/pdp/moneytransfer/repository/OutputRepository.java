package uz.pdp.moneytransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.moneytransfer.entity.Output;

import java.util.List;

@Repository
public interface OutputRepository extends JpaRepository<Output, Long> {
    List<Output> findAllByFromCard_Username(String username);
}
