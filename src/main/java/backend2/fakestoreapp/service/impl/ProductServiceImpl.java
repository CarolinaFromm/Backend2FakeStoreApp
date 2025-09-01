package backend2.fakestoreapp.service.impl;

import backend2.fakestoreapp.DTO.ProductDTO;
import backend2.fakestoreapp.DTO.RatingDTO;
import backend2.fakestoreapp.model.Product;
import backend2.fakestoreapp.model.Rating;
import backend2.fakestoreapp.repository.ProductRepository;
import backend2.fakestoreapp.service.FakeStoreClient;
import backend2.fakestoreapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FakeStoreClient fakeStoreClient;


    @Override
    public Product productDtoToEntity(ProductDTO dto) {
        return Product.builder()
                .articleId(dto.getId())
                .title(dto.getTitle())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .imageUrl(dto.getImage())
                .rating(ratingDtoToEntity(dto.getRating()))
                .build();
    }

    @Override
    public ProductDTO productEntityToDto(Product entity) {
        return ProductDTO.builder().id(entity.getArticleId()).title(entity.getTitle()).price(entity.getPrice())
                .description(entity.getDescription()).category(entity.getCategory()).image(entity.getImageUrl())
                .rating(ratingEntityToDto(entity.getRating())).build();
    }

    @Override
    public Rating ratingDtoToEntity(RatingDTO dto) {
        return Rating.builder()
                .rate(dto.getRate())
                .count(dto.getCount())
                .build();
    }

    @Override
    public RatingDTO ratingEntityToDto(Rating entity) {
        return RatingDTO.builder()
                .rate(entity.getRate())
                .count(entity.getCount())
                .build();
    }


    @Override
    public void saveProductsFromFakeStore() {
//        if (productRepository.count() > 0) {
//            return;
//        }

        productRepository.deleteAll();
        productRepository.resetAutoIncrement();
        System.out.println("Deleted all products");

        List<ProductDTO> productDTOs = fakeStoreClient.getAllProducts();

        List<Product> products = productDTOs.stream()
                .map(this::productDtoToEntity)
                .toList();

        productRepository.saveAll(products);
        System.out.println("Saved all products");
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(products -> productEntityToDto(products)).toList();
    }
    @Override
    public ProductDTO getProductById(Long id) {
        return productRepository.findByArticleId(id)
                .map(this::productEntityToDto)
                .orElseThrow(() -> new NoSuchElementException("Produkten med ID %d hittades inte".formatted(id)));
    }
}
