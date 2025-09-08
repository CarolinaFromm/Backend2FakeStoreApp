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

    @Test
    void productEntityToDto() {
        ProductDTO actual = productServiceImpl.productEntityToDto(productEntity);

        assertEquals(productEntity.getArticleId(), actual.getId(), "Should be the same id");
        assertEquals(productEntity.getTitle(), actual.getTitle(), "Should be the same title");
        assertEquals(productEntity.getPrice(), actual.getPrice(), "Should be the same price");
        assertEquals(productEntity.getDescription(), actual.getDescription(), "Should be the same description");
        assertEquals(productEntity.getCategory(), actual.getCategory(), "Should be the same category");
        assertEquals(productEntity.getImageUrl(), actual.getImage(), "Should be the same image");
        assertEquals(productEntity.getRating().getRate(), actual.getRating().getRate(), "Should be the same rating");
        assertEquals(productEntity.getRating().getCount(), actual.getRating().getCount(), "Should be the same count");
    }

    @Test
    void productDtoToEntity() {
        Product actual = productServiceImpl.productDtoToEntity(productDTO);

        assertEquals(productDTO.getId(), actual.getArticleId(), "Should be the same id");
        assertEquals(productDTO.getTitle(), actual.getTitle(), "Should be the same title");
        assertEquals(productDTO.getPrice(), actual.getPrice(), "Should be the same price");
        assertEquals(productDTO.getDescription(), actual.getDescription(), "Should be the same description");
        assertEquals(productDTO.getCategory(), actual.getCategory(), "Should be the same category");
        assertEquals(productDTO.getImage(), actual.getImageUrl(), "Should be the same image");
        assertEquals(productDTO.getRating().getRate(), actual.getRating().getRate(), "Should be the same rating");
        assertEquals(productDTO.getRating().getCount(), actual.getRating().getCount(), "Should be the same count");
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

        assertEquals(productEntity.getArticleId(), actual.getId(), "Returned DTO id should match entity article id");
        assertEquals(productEntity.getTitle(), actual.getTitle(), "Returned DTO title should match entity title");
        assertEquals(productEntity.getPrice(), actual.getPrice(), "Returned DTO price should match entity price");
        assertEquals(productEntity.getDescription(), actual.getDescription(), "Returned DTO description should match entity description");
        assertEquals(productEntity.getCategory(), actual.getCategory(), "Returned DTO category should match entity category");
        assertEquals(productEntity.getImageUrl(), actual.getImage(), "Returned DTO image should match entity image");
        assertEquals(productEntity.getRating().getRate(), actual.getRating().getRate(), "Returned DTO rating rate should match entity rating rate");
        assertEquals(productEntity.getRating().getCount(), actual.getRating().getCount(), "Returned DTO rating count should match entity rating count");
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
