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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        ProductServiceImpl service2 = new ProductServiceImpl(productRepositoryMock, fakeStoreClientMock);
        List<ProductDTO> actual = service2.getAllProducts();

        assertEquals(1, actual.size(), "Should be the same size");
    }

    //TODO använda productEntity.getArticleID?
    @Test
    void getProductById() {
        when(productRepositoryMock.findById(productEntity.getId()))
                .thenReturn(Optional.of(productEntity));

        ProductServiceImpl service2 = new ProductServiceImpl(productRepositoryMock, fakeStoreClientMock);

        ProductDTO actual = service2.getProductById(productEntity.getArticleId());

        assertEquals(productEntity.getArticleId(), actual.getId(),
                "Returned DTO id should match entity id");
    }
}
