package eg.gov.iti.jets.petstore.dto;

import eg.gov.iti.jets.petstore.entities.Address;
import eg.gov.iti.jets.petstore.entities.OrderItems;
import eg.gov.iti.jets.petstore.entities.User;
import eg.gov.iti.jets.petstore.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Details about the Order")
public class OrderDTO {

    private Long id;
    private LocalDateTime date = LocalDateTime.now();
    private Address address;
    private OrderStatus status;
    private Set<OrderItemDTO> items = new HashSet<>();

}
