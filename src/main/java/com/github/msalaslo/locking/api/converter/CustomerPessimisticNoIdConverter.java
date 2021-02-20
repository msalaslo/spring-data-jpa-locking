package com.github.msalaslo.locking.api.converter;

import org.mapstruct.Mapper;

import com.github.msalaslo.locking.api.dto.CustomerNoIdDTO;
import com.github.msalaslo.locking.domain.entity.CustomerPessimistic;

/**
 *
 * @since 1.0.0
 * 
 */
@Mapper(componentModel = "spring")
public interface CustomerPessimisticNoIdConverter {

    CustomerNoIdDTO toCustomerDto(CustomerPessimistic item);
    CustomerPessimistic toCustomer(CustomerNoIdDTO entry);

}