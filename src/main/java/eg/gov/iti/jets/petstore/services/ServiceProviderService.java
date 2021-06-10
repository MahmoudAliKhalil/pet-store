package eg.gov.iti.jets.petstore.services;

import eg.gov.iti.jets.petstore.dto.ServiceDTO;
import eg.gov.iti.jets.petstore.dto.ServiceProviderDTO;

import java.util.List;

public interface ServiceProviderService {
    List<ServiceProviderDTO> getAllServiceProviders(Integer page, Integer pageLimit);

    ServiceProviderDTO getServiceProvider(Long id);

    ServiceProviderDTO addServiceProvider(ServiceProviderDTO serviceProvider);

    ServiceProviderDTO updateServiceProvider(Long id, ServiceProviderDTO serviceProvider);

    void deleteServiceProvider(Long id);

    void deleteAllServiceProviders();

    ServiceDTO getProviderService(Long id);

}
