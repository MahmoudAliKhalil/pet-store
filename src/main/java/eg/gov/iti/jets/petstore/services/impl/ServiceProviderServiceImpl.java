package eg.gov.iti.jets.petstore.services.impl;

import eg.gov.iti.jets.petstore.dto.ServiceDTO;
import eg.gov.iti.jets.petstore.dto.ServiceProviderDTO;
import eg.gov.iti.jets.petstore.entities.ServiceProvider;
import eg.gov.iti.jets.petstore.exceptions.ResourceBadRequestException;
import eg.gov.iti.jets.petstore.exceptions.ResourceNotFoundException;
import eg.gov.iti.jets.petstore.repositories.ServiceProviderRepository;
import eg.gov.iti.jets.petstore.services.ServiceProviderService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceProviderServiceImpl implements ServiceProviderService {
    private final ServiceProviderRepository serviceProviderRepository;
    private final ModelMapper modelMapper;

    public ServiceProviderServiceImpl(ServiceProviderRepository serviceProviderRepository, ModelMapper modelMapper) {
        this.serviceProviderRepository = serviceProviderRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ServiceProviderDTO> getAllServiceProviders(Integer page, Integer pageLimit) {
        return serviceProviderRepository.findAll(Pageable.ofSize(pageLimit).withPage(page))
                .stream()
                .map(e -> modelMapper.map(e, ServiceProviderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ServiceProviderDTO getServiceProvider(Long id) {
        return serviceProviderRepository.findById(id)
                .map(e -> modelMapper.map(e, ServiceProviderDTO.class))
                .orElseThrow(() -> new ResourceBadRequestException("ServiceProvider with id: " + id + " not found"));
    }

    @Override
    public ServiceProviderDTO addServiceProvider(ServiceProviderDTO serviceProvider) {
        return modelMapper.map(serviceProviderRepository.save(modelMapper.map(serviceProvider, ServiceProvider.class)), ServiceProviderDTO.class);
    }

    @Override
    public ServiceProviderDTO updateServiceProvider(Long id, ServiceProviderDTO serviceProvider) {
        if (serviceProviderRepository.existsById(id)) {
            serviceProvider.setId(id);
            return modelMapper.map(serviceProviderRepository.save(modelMapper.map(serviceProvider, ServiceProvider.class)), ServiceProviderDTO.class);
        } else {
            throw new ResourceNotFoundException("ServiceProvider with id: " + id + " not found.");
        }
    }

    @Override
    public void deleteServiceProvider(Long id) {
        try {
            serviceProviderRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundException("ServiceProvider with id: " + id + " not found.");
        }

    }

    @Override
    public void deleteAllServiceProviders() {
        serviceProviderRepository.deleteAllInBatch();
    }

    @Override
    public ServiceDTO getProviderService(Long id) {
        return serviceProviderRepository.findById(id)
                .map(e -> modelMapper.map(e.getService(), ServiceDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("ServiceProvider with id: " + id + " not found."));
    }
}
