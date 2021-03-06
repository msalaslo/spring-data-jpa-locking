package com.github.msalaslo.locking.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.msalaslo.locking.domain.entity.Customer;
import com.github.msalaslo.locking.domain.repository.CustomerRepository;
import com.github.msalaslo.locking.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

/**
 * Sample service implementation.
 *
 * @since 1.0.0
 * 
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    

    @Override
    public Customer createCustomer(Customer customer) {
    	return customerRepository.save(customer);
    }
    
    @Override
    public void deleteCustomer(long id) {
    	customerRepository.deleteById(id);
    }
    
    
	/**
	 * Find by id and modifies data of a customer.
	 *
	 * @param name The customer to modify.
	 */
    public Customer findAndModifyCustomer(long id, Customer customer) {
        LOGGER.debug("findAndModifyCustomer::Trying to retrieve items by lastName:" + customer.getLastName() + ", firstName:" + customer.getFirstName());
        Customer customerDb = customerRepository.findById(id).get();
    	customerDb.setFirstName(customer.getFirstName());
    	customerDb.setLastName(customer.getLastName());
    	return customerRepository.save(customerDb);        
    }


	
    @Override
    public List<Customer> getCustomers() {
    	return StreamSupport
    			  .stream(customerRepository.findAll().spliterator(), false)
    			  .collect(Collectors.toList());
    }

    /**
     * Gets all IDs
     * @return list of ids
     */
    @Override
    public List<Long> findAllIds(){
    	return customerRepository.findAllIds();
    }
}
