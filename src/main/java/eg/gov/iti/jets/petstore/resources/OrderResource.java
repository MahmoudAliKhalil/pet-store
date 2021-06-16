package eg.gov.iti.jets.petstore.resources;


import eg.gov.iti.jets.petstore.dto.OrderDTO;
import eg.gov.iti.jets.petstore.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/orders/")
public class OrderResource {
    private final OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }


    @Operation(summary = "find All Order",
            description = "Retrieve all orders for admin to see users history",
            responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieve orders."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "204", description = "No order found", content = @Content)})

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        List<OrderDTO> allOrder = orderService.getAllOrder();
        return ResponseEntity.status(HttpStatus.OK).body(allOrder);
    }


//    @Operation(summary = "Finds Order by User id with order status not completed",
//            description = "Provide a user id to look up order with status not completed",
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Successfully retrieve user order."),
//                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
//                    @ApiResponse(responseCode = "204", description = "No order found", content = @Content)})
//
//    @GetMapping(path = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<OrderDTO> getOrderWithStatusNotCompletedForUser(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User Id to get his order", required = true)@PathVariable Long userId){
//        OrderDTO orderForSpecificUser = orderService.getOrderForSpecificUser(userId);
//        Link link = linkTo(OrderResource.class).slash(userId).withSelfRel();
//        orderForSpecificUser.add(link);
//        return ResponseEntity.status(HttpStatus.OK).body(orderForSpecificUser);
//    }

}
