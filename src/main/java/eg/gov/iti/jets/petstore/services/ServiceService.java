package eg.gov.iti.jets.petstore.services;


import eg.gov.iti.jets.petstore.dto.ServiceDTO;
import eg.gov.iti.jets.petstore.dto.ServicesDTO;


public interface ServiceService {

    ServicesDTO getAllServices(Float minPrice, Float maxPrice, Integer page, Integer pageLimit);
    ServiceDTO getService(Long id);
    ServiceDTO addService(ServiceDTO serviceDTO);
    ServiceDTO updateService(Long id, ServiceDTO serviceDTO);
    void  deleteService(Long id);
    void  deleteAllServices();
    ServicesDTO getServicesByTypeId(Long id, Float minPrice, Float maxPrice, Integer page, Integer pageLimit);

}
