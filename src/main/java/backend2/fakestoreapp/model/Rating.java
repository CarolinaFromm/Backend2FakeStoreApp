package backend2.fakestoreapp.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Rating {
    public double rate;
    public int count;
}
