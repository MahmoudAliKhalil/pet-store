package eg.gov.iti.jets.petstore.services;

import eg.gov.iti.jets.petstore.dto.OrderDTO;
import eg.gov.iti.jets.petstore.dto.ProductDTO;
import eg.gov.iti.jets.petstore.entities.CartItem;

import java.util.Set;

public interface ShoppingCartService {



    Set<CartItem> increaseProductQuantityInShoppingCart(Long customerId, ProductDTO productDTO);

    Set<CartItem> decreaseProductQuantityInShoppingCart(Long customerId, ProductDTO productDTO);

    Set<CartItem> removeProductFromShoppingCart(Long customerId, ProductDTO productDTO);
}
