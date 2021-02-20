package com.github.msalaslo.locking.api.converter;

import org.mapstruct.Mapper;

import com.github.msalaslo.locking.api.dto.CustomerDTO;
import com.github.msalaslo.locking.domain.entity.CustomerOptimistic;

/**
 *
 * @since 1.0.0
 * 
 */
@Mapper(componentModel = "spring")
public interface CustomerOptimisticConverter {

    CustomerDTO toCustomerDto(CustomerOptimistic item);
    CustomerOptimistic toCustomer(CustomerDTO entry);

}