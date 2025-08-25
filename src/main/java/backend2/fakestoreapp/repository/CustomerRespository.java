package backend2.fakestoreapp.repository;

import backend2.fakestoreapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRespository extends JpaRepository <Customer, Long> {
}
