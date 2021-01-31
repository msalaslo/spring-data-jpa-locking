package com.github.msalaslo.locking.domain.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.github.msalaslo.locking.domain.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	public Optional<Customer> findById(Long id);
	
	/**
	 * FindById overwritten to check as Optimistic
	 * In this case the @Version field is used by the driver to compare instances saved/update at the same time
	 * @throws If race condition happens {@link OptimisticEntityLockException}
	 * @param id the customer ID
	 * @return Optional with customer if exists
	 */
	@Lock(LockModeType.OPTIMISTIC)
	@Query("SELECT c FROM Customer c WHERE c.id = ?1")
	public Optional<Customer> findByIdWithOptimisticLocking(Long id);
	
	/**
	 * findById overwritten to check it as Pessimistic Locking
	 * In this case the @Version field is NOT used. 
	 * The database engine LOCK the result of the query (JPA execute a SELECT ... FOR UPDATE query)
	 * @throws If race condition happens {@link OptimisticEntityLockException}
	 * @param id the customer ID
	 * @return Optional with customer if exists
	 */
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT c FROM Customer c WHERE c.id = ?1")
	public Optional<Customer> findByIdWithPessimisticLocking(Long id);

}
