package com.github.msalaslo.locking.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.msalaslo.locking.domain.entity.CustomerPessimistic;
import com.github.msalaslo.locking.domain.repository.CustomerRepositoryPessimistic;
import com.github.msalaslo.locking.service.CustomerServicePessimistic;

import lombok.extern.slf4j.Slf4j;

/**
 * Sample service implementation.
 *
 * @since 1.0.0
 * 
 */
@Service
@Slf4j
public class CustomerServicePessimisticImpl implements CustomerServicePessimistic {

    @Autowired
    private CustomerRepositoryPessimistic customerRepository;
    

    @Override
    public CustomerPessimistic createCustomer(CustomerPessimistic customer) {
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
    public CustomerPessimistic findAndModifyCustomer(long id, CustomerPessimistic customer) {
        LOGGER.info("findAndModifyCustomer::Trying to retrieve items by lastName:" + customer.getLastName() + ", firstName:" + customer.getFirstName());
    	CustomerPessimistic customerDb = customerRepository.findById(id).get();
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
    public CustomerPessimistic findAndModifyCustomerWithPessimisticWriteLocking(long id, CustomerPessimistic customer) {
        LOGGER.info("findAndModifyCustomerWithPessimisticWriteLocking::Trying to retrieve items by lastName:" + customer.getLastName() + ", firstName:" + customer.getFirstName());
    	CustomerPessimistic customerDb = customerRepository.findByIdWithPessimisticWriteLocking(id).get();
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
    public CustomerPessimistic findAndModifyCustomerWithPessimisticReadLocking(long id, CustomerPessimistic customer) {
        LOGGER.info("findAndModifyCustomerWithPessimisticReadLocking::Trying to retrieve items by lastName:" + customer.getLastName() + ", firstName:" + customer.getFirstName());
    	CustomerPessimistic customerDb = customerRepository.findByIdWithPessimisticReadLocking(id).get();
    	customerDb.setFirstName(customer.getFirstName());
    	customerDb.setLastName(customer.getLastName());
    	return customerRepository.save(customerDb); 
    }
	
    @Override
    public List<CustomerPessimistic> getCustomers() {
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
