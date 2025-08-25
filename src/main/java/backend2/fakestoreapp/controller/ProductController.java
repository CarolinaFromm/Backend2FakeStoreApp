package backend2.fakestoreapp.controller;

import backend2.fakestoreapp.DTO.ProductDTO;
import backend2.fakestoreapp.service.FakeStoreClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final FakeStoreClient fakeStoreClient;

    public ProductController(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @GetMapping("/api/products/fakestore")
    public List<ProductDTO> getApiProducts() {
        return fakeStoreClient.getAllProducts();
    }
}
