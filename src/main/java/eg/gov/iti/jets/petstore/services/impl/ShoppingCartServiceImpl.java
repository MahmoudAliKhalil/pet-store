package eg.gov.iti.jets.petstore.services.impl;

import eg.gov.iti.jets.petstore.dto.*;
import eg.gov.iti.jets.petstore.entities.*;
import eg.gov.iti.jets.petstore.enums.OrderStatus;
import eg.gov.iti.jets.petstore.repositories.OrderRepository;
import eg.gov.iti.jets.petstore.services.CustomerService;
import eg.gov.iti.jets.petstore.services.ShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CustomerService customerService;

    @Autowired
    public ShoppingCartServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.customerService = customerService;
    }


    @Override
    public OrderDTO addNewProductToShoppingCart(Long customerId, ProductDTO productDTO) {

        // TODO: get shopping cart for user by user Id
        CustomerDTO customer = customerService.getCustomer(customerId);
        // TODO: create order item and add ot to cart set
        OrderItemsId orderItemsId = new OrderItemsId();
        orderItemsId.setProductId(productDTO.getId());

        Order customerShoppingCart = orderRepository.getOrderByUserIdAndOrderStatus(customerId);
        if (customerShoppingCart != null) {
            customerShoppingCart.getItems().forEach(orderItems -> {
                if (orderItems.getProduct().getId() == productDTO.getId()) {
                    OrderItems orderItem = createOrderItem(productDTO, customerShoppingCart);
                    orderItem.setQuantity(orderItems.getQuantity() + 1);
                }
            });
            return modelMapper.map(customerShoppingCart, OrderDTO.class);
        } else {
            Order newCustomerShoppingCart = new Order();
            newCustomerShoppingCart.setStatus(OrderStatus.NOT_COMPLETED);
            newCustomerShoppingCart.setDate(LocalDateTime.now());
            newCustomerShoppingCart.setAddress(modelMapper.map(customerService.getCustomer(customerId).getAddress(), Address.class));
            OrderItems orderItem = createOrderItem(productDTO, newCustomerShoppingCart);
            orderItem.setQuantity(1);
            Set<OrderItems> orderItems = new HashSet<>();
            orderItems.add(orderItem);
            newCustomerShoppingCart.setItems(orderItems);
            orderRepository.save(newCustomerShoppingCart);
            return modelMapper.map(newCustomerShoppingCart, OrderDTO.class);
        }

    }

    @Override
    public OrderDTO removeProductFromShoppingCart(Long customerId, Integer productId) {
        return null;
    }


    private OrderItems createOrderItem(ProductDTO productDTO, Order order) {
        OrderItems orderItem = new OrderItems();
        orderItem.setOrder(order);
        Product product = modelMapper.map(productDTO, Product.class);
        orderItem.setProduct(product);
        orderItem.setPriceAfterDiscount(productDTO.getDiscount() * productDTO.getPrice());
        return orderItem;
    }
}
