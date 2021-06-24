package eg.gov.iti.jets.petstore.services.impl;

import eg.gov.iti.jets.petstore.dto.ProductDTO;
import eg.gov.iti.jets.petstore.dto.ProductsDTO;
import eg.gov.iti.jets.petstore.dto.SellerDTO;
import eg.gov.iti.jets.petstore.entities.Product;
import eg.gov.iti.jets.petstore.entities.Seller;
import eg.gov.iti.jets.petstore.exceptions.ResourceNotFoundException;
import eg.gov.iti.jets.petstore.repositories.SellerRepository;
import eg.gov.iti.jets.petstore.services.SellerService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;

    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SellerDTO> getAllSellers(Integer page, Integer pageLimit) {
        return sellerRepository.findAll(Pageable.ofSize(pageLimit).withPage(page))
                .stream()
                .map(e -> modelMapper.map(e, SellerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SellerDTO getSeller(Long id) {
        return sellerRepository.findById(id)
                .map(e -> modelMapper.map(e, SellerDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Seller with id: " + id + " not found."));
    }

    @Override
    public SellerDTO addSeller(SellerDTO seller) {
        return modelMapper.map(sellerRepository.save(modelMapper.map(seller, Seller.class)), SellerDTO.class);
    }

    @Override
    public SellerDTO updateSeller(Long id, SellerDTO seller) {
        if (sellerRepository.existsById(id)) {
            seller.setId(id);
            return modelMapper.map(sellerRepository.save(modelMapper.map(seller, Seller.class)), SellerDTO.class);
        } else {
            throw new ResourceNotFoundException("Seller with id: " + id + " not found.");
        }
    }

    @Override
    public void deleteSeller(Long id) {
        try {
            sellerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundException("Seller with id: " + id + " not found.");
        }
    }

    @Override
    public void deleteAllSellers() {
        sellerRepository.deleteAllInBatch();
    }

    @Override
    public ProductsDTO getSellerProducts(Long id, Integer page, Integer pageLimit) {
        Page<Product> products = sellerRepository.getSellerProducts(id, Pageable.ofSize(pageLimit).withPage(page));
        return ProductsDTO.builder()
                .count(products.getTotalElements())
                .products(products.stream()
                        .map(e -> modelMapper.map(e, ProductDTO.class))
                        .collect(Collectors.toList()))
                .build();
    }
}
