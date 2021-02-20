package com.github.msalaslo.locking.domain.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.github.msalaslo.locking.domain.entity.CustomerPessimistic;

public interface CustomerRepositoryPessimistic extends CrudRepository<CustomerPessimistic, Long> {
	
	public Optional<CustomerPessimistic> findById(Long id);
	
	/**
	 * findById overwritten to check it as PESSIMISTIC READ Locking: Row(s) blocked only for update, delete, insert, but for read (dirty read)
	 * In this case the @Version field is NOT used. 
	 * The database engine LOCK the result of the query (JPA execute a SELECT ... FOR UPDATE query)
	 * @param id the customer ID
	 * @return Optional with customer if exists
	 */
	@Lock(LockModeType.READ)
	@Query("SELECT c FROM CustomerPessimistic c WHERE c.id = ?1")
	public Optional<CustomerPessimistic> findByIdWithPessimisticReadLocking(Long id);
	
	/**
	 * findById overwritten to check it as PESSIMISTIC WRITE Locking: Row(s) blocked for read, update, delete, insert
	 * In this case the @Version field is NOT used. 
	 * The database engine LOCK the result of the query (JPA execute a SELECT ... FOR UPDATE query)
	 * @param id the customer ID
	 * @return Optional with customer if exists
	 */
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT c FROM CustomerPessimistic c WHERE c.id = ?1")
	public Optional<CustomerPessimistic> findByIdWithPessimisticWriteLocking(Long id);
	
	
	@Query("SELECT c.id FROM CustomerPessimistic c")
	public List<Long> findAllIds();

}
