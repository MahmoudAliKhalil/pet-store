package eg.gov.iti.jets.petstore.services;

import eg.gov.iti.jets.petstore.dto.ServiceDTO;

import java.util.List;

public interface ServiceService {

    List<ServiceDTO> getAllServices(Integer page,Integer pageLimit);
    ServiceDTO getService(Long id);
    ServiceDTO addService(ServiceDTO serviceDTO);
    ServiceDTO updateService(Long id, ServiceDTO serviceDTO);
    void  deleteService(Long id);
    void  deleteAllServices();



}
