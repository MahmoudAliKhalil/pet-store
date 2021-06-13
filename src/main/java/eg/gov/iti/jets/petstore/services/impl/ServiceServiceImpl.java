package eg.gov.iti.jets.petstore.services.impl;

import eg.gov.iti.jets.petstore.dto.ServiceDTO;
import eg.gov.iti.jets.petstore.exceptions.ResourceNotFoundException;
import eg.gov.iti.jets.petstore.repositories.ServiceRepository;
import eg.gov.iti.jets.petstore.services.ServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final ModelMapper modelMapper;

    public ServiceServiceImpl(ServiceRepository serviceRepository, ModelMapper modelMapper) {
        this.serviceRepository = serviceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ServiceDTO> getAllServices(Integer page,Integer pageLimit) {
        return serviceRepository
                .findAll(Pageable.ofSize(pageLimit).withPage(page))
                .stream()
                .map(e->modelMapper.map(e,ServiceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ServiceDTO getService(Long id) {
        return serviceRepository.findById(id)
                .map(e->modelMapper.map(e,ServiceDTO.class))
                .orElseThrow(()-> new ResourceNotFoundException("Service with id: "+ id +" not found."));
    }

    @Override
    public ServiceDTO addService(ServiceDTO serviceDTO) {
        return modelMapper
                .map(serviceRepository.save(modelMapper.map(serviceDTO, eg.gov.iti.jets.petstore.entities.Service.class)),ServiceDTO.class);

    }

    @Override
    public ServiceDTO updateService(Long id, ServiceDTO serviceDTO) {
        serviceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Service with id: "+ id +" not found."));
        serviceDTO.setId(id);
        return modelMapper
                .map(serviceRepository.save(modelMapper.map(serviceDTO, eg.gov.iti.jets.petstore.entities.Service.class)),ServiceDTO.class);
    }

    @Override
    public void deleteService(Long id) {
        try {
            serviceRepository.deleteById(id);
        }catch (EmptyResultDataAccessException exception)
        {
            throw new ResourceNotFoundException("Service with id: "+ id +" not found.");
        }
    }

    @Override
    public void deleteAllServices() { serviceRepository.deleteAll();}
}
