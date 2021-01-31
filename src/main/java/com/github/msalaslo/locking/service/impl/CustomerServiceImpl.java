package com.github.msalaslo.locking.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    
	/**
	 * Find by id and modifies data of a customer.
	 *
	 * @param name The customer to modify.
	 */
    public Customer findAndModifyCustomerWithOptimisticLocking(long id, Customer customer) {
        LOGGER.debug("findAndModifyCustomerWithOptimisticLocking::Trying to retrieve items by lastName:" + customer.getLastName() + ", firstName:" + customer.getFirstName());
    	Customer customerDb = customerRepository.findByIdWithOptimisticLocking(id).get();
    	customerDb.setFirstName(customer.getFirstName());
    	customerDb.setLastName(customer.getLastName());
    	return customerRepository.save(customerDb);        
    }
    
	/**
	 * Find by id and modifies data of a customer.
	 *
	 * @param name The customer to modify.
	 */
    //@TODO 3000 seconds of transaction timeout just for testing purposes!!!
    @Transactional(readOnly = false, timeout = 3000, propagation = Propagation.REQUIRED)
    public Customer findAndModifyCustomerWithPessimisticLocking(long id, Customer customer) {
        LOGGER.debug("findAndModifyCustomerWithPessimisticLocking::Trying to retrieve items by lastName:" + customer.getLastName() + ", firstName:" + customer.getFirstName());
    	Customer customerDb = customerRepository.findByIdWithPessimisticLocking(id).get();
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

}
