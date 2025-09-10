package backend2.fakestoreapp.controller.RestController;

import backend2.fakestoreapp.DTO.ProductDTO;
import backend2.fakestoreapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductRestController {

    private static final Logger log = LoggerFactory.getLogger(ProductRestController.class);
    private final ProductService productService;

    @GetMapping("/products")
    public List<ProductDTO> retrieveAllProducts() {
        log.info("Retrieving all products");
        return productService.getAllProducts();
    }
}
