package eg.gov.iti.jets.petstore.services.impl;

import eg.gov.iti.jets.petstore.dto.OrderDTO;
import eg.gov.iti.jets.petstore.entities.Order;
import eg.gov.iti.jets.petstore.repositories.OrderRepository;
import eg.gov.iti.jets.petstore.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrderDTO> getAllOrder() {
        List<Order> orderList = orderRepository.findAll();
        return orderList
                .stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    //this method is used to get the not completed Order( Shopping cart )

//    @Override
//    public OrderDTO getOrderForSpecificUser(Long userId) {
//        Order orderByUserIdAndOrderStatus = orderRepository.getOrderByUserIdAndOrderStatus(userId);
//        return modelMapper.map(orderByUserIdAndOrderStatus, OrderDTO.class);
//    }
}
