package eg.gov.iti.jets.petstore.resources;

import eg.gov.iti.jets.petstore.dto.ProductDTO;
import eg.gov.iti.jets.petstore.services.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("images")
public class ProductImageResource {

    @Autowired
    ProductImageService productImageService;

    @PostMapping("/upload")
    public String uploadSingleImage(@RequestParam("product") ProductDTO productDTO,
                                    @RequestParam("file") MultipartFile file) {
        String keyName = file.getOriginalFilename();
        productImageService.uploadSingleImage(productDTO, file);
        return "Upload Successfully -> KeyName = " + keyName;
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<String> deleteSingleImage(@PathParam(value = "imageName") final String imageName,
                                             @PathParam("productName") final String productName,
                                             @PathParam("categoryName") final String categoryName) {

        Boolean isSingleImageDeleted = productImageService.deleteSingleImage(imageName, productName, categoryName);
        final String imagePath = categoryName + '/' + productName + '/' + imageName;
        if (isSingleImageDeleted) {
            final String response = "[" + imagePath + "] deleted successfully.";
            return new ResponseEntity<String>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping(value = "/delete/{product_id}")
    public ResponseEntity<String> deleteImagesOfProduct(@PathVariable("product_id") long id){

        Boolean isSingleImageDeleted = productImageService.deleteImagesOfProduct(id);
        if (isSingleImageDeleted) {
            return new ResponseEntity<String>("deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("failed to delete.", HttpStatus.NOT_FOUND);
        }

    }


}
