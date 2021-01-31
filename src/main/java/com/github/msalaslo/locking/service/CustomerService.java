package com.github.msalaslo.locking.service;


import java.util.List;

import com.github.msalaslo.locking.domain.entity.Customer;

/**
 * Sample service interface used as template. <b>Please remove for actual project implementation.</b>
 *
 * @since 1.0.0
 * 
 */
public interface CustomerService {

	/**
	 * Creates a customer.
	 * 
	 * @param customer The customer to create.
	 */
    Customer createCustomer(Customer customer);
    
	/**
	 * Find by id and modifies data of a customer.
	 * 
	 * @param name The customer to modify.
	 */
    Customer findAndModifyCustomer(long id, Customer customer);
    
	/**
	 * Find by id and modifies data of a customer.
	 * 
	 * @param name The customer to modify.
	 */
    Customer findAndModifyCustomerWithOptimisticLocking(long id, Customer customer);
    
	/**
	 * Find by id and modifies data of a customer.
	 * 
	 * @param name The customer to modify.
	 */
    Customer findAndModifyCustomerWithPessimisticLocking(long id, Customer customer);

	/**
	 * Gets all the customers.
	 * 
	 * @return The list of customers.
	 */
    List<Customer> getCustomers();

}
