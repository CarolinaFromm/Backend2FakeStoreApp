package backend2.fakestoreapp;

import backend2.fakestoreapp.service.impl.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Backend2FakeStoreAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(Backend2FakeStoreAppApplication.class, args);
        // Det här är en uppdaterad version av master.
        // This is a comment
    }

    @Bean
    public CommandLineRunner dataInitializer(ProductServiceImpl productServiceImpl) {
        return args -> {
            productServiceImpl.saveProductsFromFakeStore();
        };
    }
}
