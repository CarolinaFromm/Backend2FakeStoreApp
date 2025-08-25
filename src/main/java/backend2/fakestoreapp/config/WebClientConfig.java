package backend2.fakestoreapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

        @Bean
        public WebClient fakeStoreWebClient(
                @Value("${app.fakestore.fakeStoreUrl}") String fakeStoreUrl) {
            return WebClient.builder()
                    .baseUrl(fakeStoreUrl)
                    .build();
        }
    }