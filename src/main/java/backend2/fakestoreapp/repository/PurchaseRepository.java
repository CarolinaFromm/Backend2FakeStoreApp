package backend2.fakestoreapp.repository;

import backend2.fakestoreapp.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByCustomerEmailOrderByCreatedAtDesc(String email);
}
