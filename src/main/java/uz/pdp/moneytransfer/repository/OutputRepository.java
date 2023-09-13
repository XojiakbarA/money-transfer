package uz.pdp.moneytransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.moneytransfer.entity.Output;

@Repository
public interface OutputRepository extends JpaRepository<Output, Long> {
}
