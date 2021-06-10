package eg.gov.iti.jets.petstore.services.impl;

import eg.gov.iti.jets.petstore.dto.SellerDTO;
import eg.gov.iti.jets.petstore.entities.Seller;
import eg.gov.iti.jets.petstore.exceptions.SellerNotFoundException;
import eg.gov.iti.jets.petstore.repositories.SellerRepository;
import eg.gov.iti.jets.petstore.services.SellerService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
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
                .orElseThrow(() -> new SellerNotFoundException("Seller with id: " + id + " not found."));
    }

    @Override
    public SellerDTO addSeller(SellerDTO seller) {
        return modelMapper.map(sellerRepository.save(modelMapper.map(seller, Seller.class)), SellerDTO.class);
    }

    @Override
    public SellerDTO updateSeller(Long id, SellerDTO seller) {
        seller.setId(id);
        return modelMapper.map(sellerRepository.save(modelMapper.map(seller, Seller.class)), SellerDTO.class);
    }

    @Override
    public void deleteSeller(Long id) {
        try {
            sellerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new SellerNotFoundException("Seller with id: " + id + " not found.");
        }
    }

    @Override
    public void deleteAllSellers() {
        sellerRepository.deleteAllInBatch();
    }
}
