package eg.gov.iti.jets.petstore.services;

import eg.gov.iti.jets.petstore.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getAllOrder();

//    OrderDTO getOrderForSpecificUser(Long userId);

}
