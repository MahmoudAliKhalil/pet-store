package eg.gov.iti.jets.petstore.services;

import eg.gov.iti.jets.petstore.dto.AdminDTO;

import java.util.List;

public interface AdminService {
    List<AdminDTO> getAllAdmins(Integer page, Integer pageLimit);

    AdminDTO getAdmin(Long id);

    AdminDTO addAdmin(AdminDTO admin);

    AdminDTO updateAdmin(Long id, AdminDTO admin);

    void deleteAdmin(Long id);

    void deleteAllAdmins();
}
