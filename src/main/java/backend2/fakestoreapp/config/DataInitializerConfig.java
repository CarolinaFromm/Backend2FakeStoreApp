package backend2.fakestoreapp.config;

import backend2.fakestoreapp.model.Role;
import backend2.fakestoreapp.repository.RoleRepository;
import backend2.fakestoreapp.service.impl.ProductServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializerConfig {

    private final RoleRepository roleRepository;

    public DataInitializerConfig(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void confirmRolesToLoweCase() {
        if (roleRepository.findByName("user").isEmpty()) {
            Role user = new Role();
            user.setName("user");
            roleRepository.save(user);
        }

        if (roleRepository.findByName("admin").isEmpty()) {
            Role admin = new Role();
            admin.setName("admin");
            roleRepository.save(admin);
        }
    }

    @Bean
    public CommandLineRunner dataInitializer(ProductServiceImpl productServiceImpl) {
        return args -> {
            productServiceImpl.saveProductsFromFakeStore();
        };
    }

}
