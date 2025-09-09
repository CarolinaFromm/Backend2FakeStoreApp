package backend2.fakestoreapp.service.impl;

import backend2.fakestoreapp.DTO.ProductDTO;
import backend2.fakestoreapp.DTO.RatingDTO;
import backend2.fakestoreapp.model.Product;
import backend2.fakestoreapp.model.Rating;
import backend2.fakestoreapp.repository.ProductRepository;
import backend2.fakestoreapp.service.FakeStoreClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepositoryMock;

    @Mock
    private FakeStoreClient fakeStoreClientMock;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    Product productEntity = new Product(1L, 1L, "Iphone", 999.99,
            "Model 2025", "electronics", "http://example.com/image.jpg",
            new Rating(4.5, 100));

    ProductDTO productDTO = new ProductDTO(1L, "Iphone", 999.99, "Model 2025",
            "electronics", "http://example.com/image.jpg",
            new RatingDTO(4.5, 100));

    private void assertProductEquals(Product entity, ProductDTO dto) {
        assertEquals(entity.getArticleId(), dto.getId(), "ID should match");
        assertEquals(entity.getTitle(), dto.getTitle(), "Title should match");
        assertEquals(entity.getPrice(), dto.getPrice(), "Price should match");
        assertEquals(entity.getDescription(), dto.getDescription(), "Description should match");
        assertEquals(entity.getCategory(), dto.getCategory(), "Category should match");
        assertEquals(entity.getImageUrl(), dto.getImage(), "Image should match");
        assertEquals(entity.getRating().getRate(), dto.getRating().getRate(), "Rating rate should match");
        assertEquals(entity.getRating().getCount(), dto.getRating().getCount(), "Rating count should match");
    }

    @Test
    void productEntityToDto() {
        ProductDTO actual = productServiceImpl.productEntityToDto(productEntity);
        assertProductEquals(productEntity, actual);
    }

    @Test
    void productDtoToEntity() {
        Product actual = productServiceImpl.productDtoToEntity(productDTO);

        assertEquals(productDTO.getId(), actual.getArticleId());
        assertEquals(productDTO.getTitle(), actual.getTitle());
        assertEquals(productDTO.getPrice(), actual.getPrice());
        assertEquals(productDTO.getDescription(), actual.getDescription());
        assertEquals(productDTO.getCategory(), actual.getCategory());
        assertEquals(productDTO.getImage(), actual.getImageUrl());
        assertEquals(productDTO.getRating().getRate(), actual.getRating().getRate());
        assertEquals(productDTO.getRating().getCount(), actual.getRating().getCount());
    }

    @Test
    void getAllProducts() {
        when(productRepositoryMock.findAll()).thenReturn(Arrays.asList(productEntity));

        List<ProductDTO> actual = productServiceImpl.getAllProducts();

        assertEquals(1, actual.size(), "Should be the same size");
    }

    @Test
    void getProductById_found() {
        when(productRepositoryMock.findById(productEntity.getId()))
                .thenReturn(Optional.of(productEntity));

        ProductDTO actual = productServiceImpl.getProductById(productEntity.getId());

        assertProductEquals(productEntity, actual);
    }

    @Test
    void getProductById_notFound() {
        Long missingId = 999L;
        when(productRepositoryMock.findById(missingId)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> productServiceImpl.getProductById(missingId),
                "Should throw NoSuchElementException if product not found"
        );

        assertEquals("Produkten med ID 999 hittades inte", exception.getMessage(),
                "Exception message should match the expected format");
    }
}
