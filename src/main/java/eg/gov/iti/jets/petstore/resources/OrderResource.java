package eg.gov.iti.jets.petstore.resources;


import eg.gov.iti.jets.petstore.dto.OrderDTO;
import eg.gov.iti.jets.petstore.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders/")
public class OrderResource {
    private final OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "get All Order",
            description = "get all orders for admin to see users history"
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        List<OrderDTO> allOrder = orderService.getAllOrder();
        return ResponseEntity.status(HttpStatus.OK).body(allOrder);
    }

    @GetMapping(path = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finds Order by User id with order status not completed",
            description = "Provide a user id to look up order with status not completed"
    )
    public ResponseEntity<OrderDTO> getOrderWithStatusNotCompletedForUser(@PathVariable Long userId){
        OrderDTO orderForSpecificUser = orderService.getOrderForSpecificUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderForSpecificUser);
    }

}
