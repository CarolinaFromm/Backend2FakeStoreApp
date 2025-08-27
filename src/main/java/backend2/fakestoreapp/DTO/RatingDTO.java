package backend2.fakestoreapp.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class RatingDTO {
    private double rate;
    private Integer count;
}
