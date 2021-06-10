package eg.gov.iti.jets.petstore.services.Impl;


import eg.gov.iti.jets.petstore.dto.CustomerDTO;
import eg.gov.iti.jets.petstore.entities.Customer;
import eg.gov.iti.jets.petstore.exceptions.CustomerNotFoundException;
import eg.gov.iti.jets.petstore.repositories.CustomerRepository;
import eg.gov.iti.jets.petstore.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<CustomerDTO> getAllCustomers(Integer page, Integer pageLimit) {
        return customerRepository.findAll(Pageable.ofSize(pageLimit).withPage(page))
                .stream()
                .map(e -> modelMapper.map(e, CustomerDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public CustomerDTO getCustomer(Long id) {
        return customerRepository.findById(id)
                .map(e -> modelMapper.map(e, CustomerDTO.class))
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id: " + id + " not found."));
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customer) {
        return modelMapper.map(customerRepository.save(modelMapper.map(customer, Customer.class)), CustomerDTO.class);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customer) {
        customer.setId(id);
        return modelMapper.map(customerRepository.save(modelMapper.map(customer, Customer.class)), CustomerDTO.class);
    }

    @Override
    public void deleteCustomer(Long id) {
        try {
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new CustomerNotFoundException("Customer with id: " + id + " not found.");
        }
    }

    @Override
    public void deleteAllCustomers() {
        customerRepository.deleteAllInBatch();
    }
}
