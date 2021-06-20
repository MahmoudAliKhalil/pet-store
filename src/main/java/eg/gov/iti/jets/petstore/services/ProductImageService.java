package eg.gov.iti.jets.petstore.services;

import eg.gov.iti.jets.petstore.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ProductImageService {

    public void uploadSingleImage(ProductDTO productDTO, MultipartFile file);
    public Boolean deleteSingleImage(String imageName, String productName, String categoryName);
    public Boolean deleteImagesOfProduct(long id);
}
