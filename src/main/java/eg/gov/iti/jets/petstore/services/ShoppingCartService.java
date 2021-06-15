package eg.gov.iti.jets.petstore.services;

import eg.gov.iti.jets.petstore.dto.OrderDTO;
import eg.gov.iti.jets.petstore.dto.ProductDTO;

public interface ShoppingCartService {

    OrderDTO addNewProductToShoppingCart(Long customerId, ProductDTO productDTO);
    OrderDTO removeProductFromShoppingCart(Long customerId, Integer productId);
}
