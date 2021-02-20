package com.github.msalaslo.locking.domain.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.github.msalaslo.locking.domain.entity.CustomerOptimistic;

public interface CustomerRepositoryOptimistic extends CrudRepository<CustomerOptimistic, Long> {
	
	public Optional<CustomerOptimistic> findById(Long id);
	
	/**
	 * FindById overwritten to check as Optimistic
	 * In this case the @Version field is used by the driver to compare instances saved/update at the same time
	 * @throws If race condition happens {@link OptimisticEntityLockException}
	 * @param id the customer ID
	 * @return Optional with customer if exists
	 */
	@Lock(LockModeType.OPTIMISTIC)
	@Query("SELECT c FROM CustomerOptimistic c WHERE c.id = ?1")
	public Optional<CustomerOptimistic> findByIdWithOptimisticLocking(Long id);
	
	
	@Query("SELECT c.id FROM CustomerOptimistic c")
	public List<Long> findAllIds();


}
