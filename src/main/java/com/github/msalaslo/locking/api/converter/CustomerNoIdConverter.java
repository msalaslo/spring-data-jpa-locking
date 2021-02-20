package com.github.msalaslo.locking.api.converter;

import org.mapstruct.Mapper;

import com.github.msalaslo.locking.api.dto.CustomerNoIdDTO;
import com.github.msalaslo.locking.domain.entity.Customer;

/**
 *
 * @since 1.0.0
 * 
 */
@Mapper(componentModel = "spring")
public interface CustomerNoIdConverter {

    CustomerNoIdDTO toCustomerDto(Customer item);
    Customer toCustomer(CustomerNoIdDTO entry);

}