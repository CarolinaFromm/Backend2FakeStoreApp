package backend2.fakestoreapp.service;

import backend2.fakestoreapp.DTO.ProductDTO;
import backend2.fakestoreapp.DTO.RatingDTO;
import backend2.fakestoreapp.model.Product;
import backend2.fakestoreapp.model.Rating;

public interface ProductService {

    public Product productDtoToEntity(ProductDTO productDTO);
    public Rating ratingDtoToEntity(RatingDTO dto);
    public RatingDTO ratingEntityToDto(Rating entity);

    public void saveProductsFromFakeStore();
}
