package backend2.fakestoreapp.repository;

import backend2.fakestoreapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Transactional
    @Query(value = "ALTER SEQUENCE product_id_seq RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

    Optional<Product> findByArticleId(Long articleId);
}
