package eg.gov.iti.jets.petstore.services.impl;

import eg.gov.iti.jets.petstore.dto.ProductDTO;
import eg.gov.iti.jets.petstore.entities.CartItem;
import eg.gov.iti.jets.petstore.entities.Customer;
import eg.gov.iti.jets.petstore.entities.Product;
import eg.gov.iti.jets.petstore.exceptions.ResourceNotFoundException;
import eg.gov.iti.jets.petstore.repositories.CustomerRepository;
import eg.gov.iti.jets.petstore.repositories.ProductRepository;
import eg.gov.iti.jets.petstore.services.ShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ShoppingCartServiceImpl(CustomerRepository customerRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
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
    public Set<CartItem> updateProductFromShoppingCart(Long customerId, ProductDTO productDTO, Integer quantity) {

        //TODO: Handle bad request
        Product product = productRepository.findById(productDTO.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Product with id "+ productDTO.getId()+" not found"));
        int availableQuantity = product.getQuantity();
        //to check if quantity is available
        if (quantity > availableQuantity)
            throw new ResourceNotFoundException("A maximum of " + availableQuantity + " items is available from this product");

        Customer customer = customerRepository.getById(customerId);
        Set<CartItem> shoppingCart = customer.getShoppingCart();
        Optional<CartItem> optionalItem = shoppingCart
                .stream()
                .filter(s -> s.getProduct().getId() == productDTO.getId())
                .findFirst();

        CartItem cartItem;
        if (optionalItem.isPresent()) {
            cartItem = optionalItem.get();
            //to check if quantity in cart and new quantity are less than available quantity
            if ((cartItem.getQuantity() + quantity) > availableQuantity)
                throw new ResourceNotFoundException("A maximum of " + availableQuantity + " items is available from this product");
            else {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            }
        } else {
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setProduct(modelMapper.map(productDTO, Product.class));
        }

        shoppingCart.add(cartItem);
        customer.setShoppingCart(shoppingCart);
        customerRepository.save(customer);
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


}
