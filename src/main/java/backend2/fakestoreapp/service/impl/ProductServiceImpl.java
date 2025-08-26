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

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FakeStoreClient fakeStoreClient;


    @Override
    public Product productDtoToEntity(ProductDTO dto) {
        return Product.builder().articleId(dto.getId()).title(dto.getTitle()).price(dto.getPrice())
                .description(dto.getDescription()).category(dto.getCategory()).imageUrl(dto.getImage())
                .rating(ratingDtoToEntity(dto.getRating()))
                .build();
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
        RatingDTO dto = new RatingDTO();
        dto.setRate(entity.getRate());
        dto.setCount(entity.getCount());
        return dto;
    }

    @Override
    public void saveProductsFromFakeStore() {
        if (productRepository.count() > 0) {
            return;
        }

        List<ProductDTO> productDTOs = fakeStoreClient.getAllProducts();

        List<Product> products = productDTOs.stream()
                .map(this::productDtoToEntity)
                .toList();

        productRepository.saveAll(products);
    }
}
