package eg.gov.iti.jets.petstore.services.impl;

import eg.gov.iti.jets.petstore.dto.ProductDTO;
import eg.gov.iti.jets.petstore.entities.CartItem;
import eg.gov.iti.jets.petstore.entities.Customer;
import eg.gov.iti.jets.petstore.entities.Product;
import eg.gov.iti.jets.petstore.exceptions.ResourceNotFoundException;
import eg.gov.iti.jets.petstore.repositories.CustomerRepository;
import eg.gov.iti.jets.petstore.services.ShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public ShoppingCartServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Set<CartItem> increaseProductQuantityInShoppingCart(Long customerId, ProductDTO productDTO) {
        //TODO we create the cart in regestering user
        //TODO if user wants to add to cart without login, he redirects to login
        // TODO: get shopping cart for user by user Id
        Set<CartItem> shoppingCart = customerRepository.findShoppingCartById(customerId);
        Optional<CartItem> optionalItem = shoppingCart.stream().filter(s -> s.getProduct().getId() == productDTO.getId()).findFirst();
        if (optionalItem.isPresent())
            optionalItem.get().increaseQuantity();
        else {
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(1);
            cartItem.setProduct(modelMapper.map(productDTO, Product.class));
            shoppingCart.add(cartItem);
        }
        return shoppingCart;
    }

    @Override
    public Set<CartItem> decreaseProductQuantityInShoppingCart(Long customerId, ProductDTO productDTO) {
        Set<CartItem> shoppingCart = customerRepository.findShoppingCartById(customerId);
        Optional<CartItem> optionalItem = shoppingCart.stream().filter(s -> s.getProduct().getId() == productDTO.getId()).findFirst();
        if (optionalItem.isPresent())
            optionalItem.get().decreaseQuantity();
        else
            throw new ResourceNotFoundException("Product isn't in your cart");



        return shoppingCart;

//        Set<CartItem> shoppingCart = customerRepository.findShoppingCartById(customerId);
//        CartItem cartItem = new CartItem();
//        cartItem.setProduct(modelMapper.map(productDTO, Product.class));
//        if (shoppingCart.contains(cartItem))
//            cartItem.decreaseQuantity();
//        else
//            cartItem.setQuantity(1);
//        shoppingCart.add(cartItem);
//        return shoppingCart;
    }

    @Override
    public Set<CartItem> removeProductFromShoppingCart(Long customerId, ProductDTO productDTO) {
        Set<CartItem> shoppingCart = customerRepository.findShoppingCartById(customerId);
        Optional<CartItem> optionalItem = shoppingCart.stream().filter(s -> s.getProduct().getId() == productDTO.getId()).findFirst();
        if (optionalItem.isPresent())
            shoppingCart.remove(optionalItem.get());
        else
            throw new ResourceNotFoundException("Product isn't in your cart");
        return shoppingCart;
    }

    public Set<CartItem> updateProductFromShoppingCart(Long customerId, ProductDTO productDTO,Integer quantity) {
        Set<CartItem> shoppingCart = customerRepository.findShoppingCartById(customerId);
        Optional<CartItem> optionalItem = shoppingCart
                .stream()
                .filter(s -> s.getProduct().getId() == productDTO.getId())
                .findFirst();

        if (optionalItem.isPresent()){
            CartItem cartItem = optionalItem.get();
            cartItem.setQuantity(productDTO.getQuantity());
            shoppingCart.add(cartItem);
            Customer customer = customerRepository.getById(customerId);
            customer.setShoppingCart(shoppingCart);
            customerRepository.save(customer);
        }
        else
            throw new ResourceNotFoundException("Product isn't in your cart");
        return shoppingCart;
    }



}
