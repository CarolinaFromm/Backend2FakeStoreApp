package backend2.fakestoreapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;
    private String address;
    private String email;
    private String phone;
    private String password;

    @OneToMany (mappedBy = "customer")
    private Set<Purchase> purchases = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "customer_roles",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set <Role> roles = new HashSet<>();

    // Säkerställer att mejladress blir små@bokstäver.com - så unique blir relevant.
    // Finns toLowerCase på andra ställen, men flera safeguards är bra!
    @PrePersist
    @PreUpdate
    private void normalizeEmail() {
        if (email != null) {
            email = email.trim().toLowerCase();
        }
    }

}
