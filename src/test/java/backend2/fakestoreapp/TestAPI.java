package backend2.fakestoreapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestAPI {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnOkForHTTPRequestForLocalHost(){
        ResponseEntity<String> response =
                restTemplate.getForEntity("http://localhost:" + port + "/api/products/fakestore", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldReturnOkForHTTPRequest() {
        ResponseEntity<String> response =
                restTemplate.getForEntity("https://fakestoreapi.com/products", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

//    @SneakyThrows
    @Test
    void JSONObjectHasAllAttributes () throws JsonProcessingException {
        ResponseEntity<String> response =
                restTemplate.getForEntity("https://fakestoreapi.com/products", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode product = mapper.readTree(response.getBody());
        JsonNode firstProduct = product.get(0);

        assertThat(firstProduct.has("id")).isTrue();
        assertThat(firstProduct.has("title")).isTrue();
        assertThat(firstProduct.has("price")).isTrue();
        assertThat(firstProduct.has("description")).isTrue();
        assertThat(firstProduct.has("category")).isTrue();
        assertThat(firstProduct.has("image")).isTrue();
        assertThat(firstProduct.has("rating")).isTrue();

        JsonNode rating = firstProduct.get("rating");
        assertThat(rating.has("rate")).isTrue();
        assertThat(rating.has("count")).isTrue();

    }
}
