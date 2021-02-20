package com.github.msalaslo.locking.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.msalaslo.locking.domain.entity.CustomerOptimistic;
import com.github.msalaslo.locking.domain.repository.CustomerRepositoryOptimistic;
import com.github.msalaslo.locking.service.CustomerServiceOptimistic;

import lombok.extern.slf4j.Slf4j;

/**
 * Sample service implementation.
 *
 * @since 1.0.0
 * 
 */
@Service
@Slf4j
public class CustomerServiceOptimisticImpl implements CustomerServiceOptimistic {

    @Autowired
    private CustomerRepositoryOptimistic customerRepository;
    

    @Override
    public CustomerOptimistic createCustomer(CustomerOptimistic customer) {
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
    public CustomerOptimistic findAndModifyCustomer(long id, CustomerOptimistic customer) {
        LOGGER.debug("findAndModifyCustomer::Trying to retrieve items by lastName:" + customer.getLastName() + ", firstName:" + customer.getFirstName());
        CustomerOptimistic customerDb = customerRepository.findById(id).get();
    	customerDb.setFirstName(customer.getFirstName());
    	customerDb.setLastName(customer.getLastName());
    	return customerRepository.save(customerDb);        
    }
    
	/**
	 * Find by id and modifies data of a customer.
	 *
	 * @param name The customer to modify.
	 */
    public CustomerOptimistic findAndModifyCustomerWithOptimisticLocking(long id, CustomerOptimistic customer) {
        LOGGER.debug("findAndModifyCustomerWithOptimisticLocking::Trying to retrieve items by lastName:" + customer.getLastName() + ", firstName:" + customer.getFirstName());
        CustomerOptimistic customerDb = customerRepository.findByIdWithOptimisticLocking(id).get();
    	customerDb.setFirstName(customer.getFirstName());
    	customerDb.setLastName(customer.getLastName());
    	return customerRepository.save(customerDb);        
    }
    

	
    @Override
    public List<CustomerOptimistic> getCustomers() {
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
