package eg.gov.iti.jets.petstore.services.impl;

import eg.gov.iti.jets.petstore.dto.AdminDTO;
import eg.gov.iti.jets.petstore.dto.UserRegistrationDTO;
import eg.gov.iti.jets.petstore.entities.Admin;
import eg.gov.iti.jets.petstore.exceptions.ResourceNotFoundException;
import eg.gov.iti.jets.petstore.repositories.AdminRepository;
import eg.gov.iti.jets.petstore.services.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<AdminDTO> getAllAdmins(Integer page, Integer pageLimit) {
        return adminRepository.findAll(Pageable.ofSize(pageLimit).withPage(page))
                .stream()
                .map(e -> modelMapper.map(e, AdminDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AdminDTO getAdmin(Long id) {
        return adminRepository.findById(id)
                .map(e -> modelMapper.map(e, AdminDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Admin with id: " + id + " not found."));
    }

    @Override
    public AdminDTO addAdmin(AdminDTO admin) {
        return modelMapper.map(adminRepository.save(modelMapper.map(admin, Admin.class)), AdminDTO.class);
    }

    @Override
    public AdminDTO updateAdmin(Long id, AdminDTO admin) {
        admin.setId(id);
        return modelMapper.map(adminRepository.save(modelMapper.map(admin, Admin.class)), AdminDTO.class);
    }

    @Override
    public void deleteAdmin(Long id) {
        try {
            adminRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundException("Admin with id: " + id + " not found.");
        }
    }

    @Override
    public void deleteAllAdmins() {
        adminRepository.deleteAllInBatch();
    }

    @Override
    public void signUp(UserRegistrationDTO userRegistrationDTO) {
        userRegistrationDTO.setPassword(bCryptPasswordEncoder.encode(userRegistrationDTO.getPassword()));
        Admin admin = modelMapper.map(userRegistrationDTO, Admin.class);
        admin.setActive(true);
        admin.setNotLocked(true);
        adminRepository.save(admin);
    }
}
