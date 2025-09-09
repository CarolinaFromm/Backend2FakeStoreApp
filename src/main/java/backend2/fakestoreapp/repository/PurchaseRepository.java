package backend2.fakestoreapp.repository;

import backend2.fakestoreapp.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
