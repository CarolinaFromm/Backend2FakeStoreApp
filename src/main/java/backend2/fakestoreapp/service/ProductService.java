package backend2.fakestoreapp.service;

import backend2.fakestoreapp.DTO.ProductDTO;
import backend2.fakestoreapp.DTO.RatingDTO;
import backend2.fakestoreapp.model.Product;
import backend2.fakestoreapp.model.Rating;

import java.util.List;

public interface ProductService {

    public Product productDtoToEntity(ProductDTO productDTO);
    public ProductDTO productEntityToDto(Product product);
    public Rating ratingDtoToEntity(RatingDTO dto);
    public RatingDTO ratingEntityToDto(Rating entity);

    public void saveProductsFromFakeStore();
    public List<ProductDTO> getAllProducts();
    public ProductDTO getProductById(Long id);
}
