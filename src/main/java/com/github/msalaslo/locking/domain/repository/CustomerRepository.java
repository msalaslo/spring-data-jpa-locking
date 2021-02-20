package com.github.msalaslo.locking.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.github.msalaslo.locking.domain.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	public Optional<Customer> findById(Long id);

	@Query("SELECT c.id FROM Customer c")
	public List<Long> findAllIds();
}
