package backend2.fakestoreapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Customer> customers = new HashSet<>();
}
