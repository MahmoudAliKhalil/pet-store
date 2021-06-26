package eg.gov.iti.jets.petstore.services;

import eg.gov.iti.jets.petstore.dto.ProductDTO;
import eg.gov.iti.jets.petstore.dto.SellerDTO;
import eg.gov.iti.jets.petstore.dto.UserRegistrationDTO;

import java.util.List;

public interface SellerService {
    List<SellerDTO> getAllSellers(Integer page, Integer pageLimit);

    SellerDTO getSeller(Long id);

    SellerDTO addSeller(SellerDTO seller);

    SellerDTO updateSeller(Long id, SellerDTO seller);

    void deleteSeller(Long id);

    void deleteAllSellers();

    List<ProductDTO> getSellerProducts(Long id);
    void signUp(UserRegistrationDTO userRegistrationDTO);
}
