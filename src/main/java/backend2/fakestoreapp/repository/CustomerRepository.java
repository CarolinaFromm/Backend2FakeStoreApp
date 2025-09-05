package backend2.fakestoreapp.repository;

import backend2.fakestoreapp.model.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @EntityGraph(attributePaths = "roles")
    Optional<Customer> findByEmail(String email);

    boolean existsByEmail(String email);
}
