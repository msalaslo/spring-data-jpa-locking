package com.github.msalaslo.locking.service;


import java.util.List;

import com.github.msalaslo.locking.domain.entity.CustomerPessimistic;

/**
 * Sample service interface used as template. <b>Please remove for actual project implementation.</b>
 *
 * @since 1.0.0
 * 
 */
public interface CustomerServicePessimistic {

	/**
	 * Create a customer.
	 * 
	 * @param customer The customer to create.
	 */
	CustomerPessimistic createCustomer(CustomerPessimistic customer);
	
	
	/**
	 * Delete a customer.
	 * 
	 * @param id The  id customer to delete.
	 */
	void deleteCustomer(long id);
    
	/**
	 * Find by id and modifies data of a customer.
	 * 
	 * @param name The customer to modify.
	 */
	CustomerPessimistic findAndModifyCustomer(long id, CustomerPessimistic customer);
    
    
	/**
	 * Find by id and modifies data of a customer.
	 * 
	 * @param name The customer to modify.
	 */
	CustomerPessimistic findAndModifyCustomerWithPessimisticWriteLocking(long id, CustomerPessimistic customer);
	
	/**
	 * Find by id and modifies data of a customer.
	 * 
	 * @param name The customer to modify.
	 */
	CustomerPessimistic findAndModifyCustomerWithPessimisticReadLocking(long id, CustomerPessimistic customer);

	/**
	 * Gets all the customers.
	 * 
	 * @return The list of customers.
	 */
    List<CustomerPessimistic> getCustomers();
    
    /**
     * Gets all IDs
     * @return list of ids
     */
    public List<Long> findAllIds();

}
