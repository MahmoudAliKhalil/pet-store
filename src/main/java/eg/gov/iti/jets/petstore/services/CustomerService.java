package eg.gov.iti.jets.petstore.services;

import eg.gov.iti.jets.petstore.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers(Integer page, Integer pageLimit);

    CustomerDTO getCustomer(Long id);

    CustomerDTO addCustomer(CustomerDTO customer);

    CustomerDTO updateCustomer(Long id, CustomerDTO customer);

    void deleteCustomer(Long id);

    void deleteAllCustomers();
}
