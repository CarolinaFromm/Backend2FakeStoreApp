package backend2.fakestoreapp.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseDeleteDTO {
    private List<Long> purchaseIds;
}
