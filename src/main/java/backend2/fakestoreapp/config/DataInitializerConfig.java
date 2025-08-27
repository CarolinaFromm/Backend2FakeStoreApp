package backend2.fakestoreapp.config;

import backend2.fakestoreapp.service.impl.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializerConfig {

    @Bean
    public CommandLineRunner dataInitializer(ProductServiceImpl productServiceImpl) {
        return args -> {
            productServiceImpl.saveProductsFromFakeStore();
        };
    }

}
