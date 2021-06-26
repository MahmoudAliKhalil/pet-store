package eg.gov.iti.jets.petstore.services;

import eg.gov.iti.jets.petstore.dto.ServiceProviderDTO;
import eg.gov.iti.jets.petstore.dto.ServicesDTO;

import java.util.List;

public interface ServiceProviderService {
    List<ServiceProviderDTO> getAllServiceProviders(Integer page, Integer pageLimit);

    ServiceProviderDTO getServiceProvider(Long id);

    ServiceProviderDTO addServiceProvider(ServiceProviderDTO serviceProvider);

    ServiceProviderDTO updateServiceProvider(Long id, ServiceProviderDTO serviceProvider);

    void deleteServiceProvider(Long id);

    void deleteAllServiceProviders();

    ServicesDTO getProviderServices(Long id, Integer page, Integer pageLimit);

}
