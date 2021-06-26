package eg.gov.iti.jets.petstore.services.impl;

import eg.gov.iti.jets.petstore.dto.AdminDTO;
import eg.gov.iti.jets.petstore.dto.AdminsDTO;
import eg.gov.iti.jets.petstore.entities.Admin;
import eg.gov.iti.jets.petstore.exceptions.ResourceNotFoundException;
import eg.gov.iti.jets.petstore.repositories.AdminRepository;
import eg.gov.iti.jets.petstore.services.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository sellerRepository;
    private final ModelMapper modelMapper;

    public AdminServiceImpl(AdminRepository sellerRepository, ModelMapper modelMapper) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AdminsDTO getAllAdmins(Integer page, Integer pageLimit) {
        Page<Admin> admins = sellerRepository.findAll(Pageable.ofSize(pageLimit).withPage(page));
        return AdminsDTO.builder()
                .count(admins.getTotalElements())
                .admins(admins
                        .map(e -> modelMapper.map(e, AdminDTO.class))
                        .getContent())
                .build();
    }

    @Override
    public AdminDTO getAdmin(Long id) {
        return sellerRepository.findById(id)
                .map(e -> modelMapper.map(e, AdminDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Admin with id: " + id + " not found."));
    }

    @Override
    public AdminDTO addAdmin(AdminDTO admin) {
        return modelMapper.map(sellerRepository.save(modelMapper.map(admin, Admin.class)), AdminDTO.class);
    }

    @Override
    public AdminDTO updateAdmin(Long id, AdminDTO admin) {
        admin.setId(id);
        return modelMapper.map(sellerRepository.save(modelMapper.map(admin, Admin.class)), AdminDTO.class);
    }

    @Override
    public void deleteAdmin(Long id) {
        try {
            sellerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundException("Admin with id: " + id + " not found.");
        }
    }

    @Override
    public void deleteAllAdmins() {
        sellerRepository.deleteAllInBatch();
    }
}
