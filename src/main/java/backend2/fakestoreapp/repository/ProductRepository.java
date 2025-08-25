package backend2.fakestoreapp.repository;

import backend2.fakestoreapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
