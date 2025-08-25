package backend2.fakestoreapp.service;

import backend2.fakestoreapp.DTO.ProductDTO;
import backend2.fakestoreapp.config.WebClientConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreClient {

    private final WebClient webClient;

    public FakeStoreClient(WebClient fakeStoreWebClient) {
        this.webClient = fakeStoreWebClient;
    }

    public List<ProductDTO> getAllProducts() {
        ProductDTO[] products = webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToMono(ProductDTO[].class)
                .block();

        return products != null ? Arrays.asList(products) : List.of();
    }
}