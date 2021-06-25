package eg.gov.iti.jets.petstore.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PaymentDTO {
    // the product name
    private String name;
    //  currency like usd, eur ...
    private String currency;
    // our success and cancel url stripe will redirect to this links
    private String successUrl;
    private String cancelUrl;
    private long amount;
    private long quantity;
}
