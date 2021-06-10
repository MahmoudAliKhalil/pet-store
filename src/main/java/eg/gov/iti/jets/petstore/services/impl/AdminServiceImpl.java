package eg.gov.iti.jets.petstore.services.Impl;

import eg.gov.iti.jets.petstore.dto.AdminDTO;
import eg.gov.iti.jets.petstore.entities.Admin;
import eg.gov.iti.jets.petstore.exceptions.AdminNotFoundException;
import eg.gov.iti.jets.petstore.repositories.AdminRepository;
import eg.gov.iti.jets.petstore.services.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository sellerRepository;
    private final ModelMapper modelMapper;

    public AdminServiceImpl(AdminRepository sellerRepository, ModelMapper modelMapper) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AdminDTO> getAllAdmins(Integer page, Integer pageLimit) {
        return sellerRepository.findAll(Pageable.ofSize(pageLimit).withPage(page))
                .stream()
                .map(e -> modelMapper.map(e, AdminDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AdminDTO getAdmin(Long id) {
        return sellerRepository.findById(id)
                .map(e -> modelMapper.map(e, AdminDTO.class))
                .orElseThrow(() -> new AdminNotFoundException("Admin with id: " + id + " not found."));
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
            throw new AdminNotFoundException("Admin with id: " + id + " not found.");
        }
    }

    @Override
    public void deleteAllAdmins() {
        sellerRepository.deleteAllInBatch();
    }
}
