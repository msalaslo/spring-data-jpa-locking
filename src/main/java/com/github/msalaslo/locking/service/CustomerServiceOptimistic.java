package com.github.msalaslo.locking.service;


import java.util.List;

import com.github.msalaslo.locking.domain.entity.CustomerOptimistic;

/**
 * Sample service interface used as template. <b>Please remove for actual project implementation.</b>
 *
 * @since 1.0.0
 * 
 */
public interface CustomerServiceOptimistic {

	/**
	 * Creates a customer.
	 * 
	 * @param customer The customer to create.
	 */
    CustomerOptimistic createCustomer(CustomerOptimistic customer);
    
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
    CustomerOptimistic findAndModifyCustomer(long id, CustomerOptimistic customer);
    
	/**
	 * Find by id and modifies data of a customer.
	 * 
	 * @param name The customer to modify.
	 */
    CustomerOptimistic findAndModifyCustomerWithOptimisticLocking(long id, CustomerOptimistic customer);
    
	/**
	 * Gets all the customers.
	 * 
	 * @return The list of customers.
	 */
    List<CustomerOptimistic> getCustomers();

    /**
     * Gets all IDs
     * @return list of ids
     */
    public List<Long> findAllIds();
}
