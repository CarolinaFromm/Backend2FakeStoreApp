package backend2.fakestoreapp.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Rating {
    public double rate;
    public int count;
}
