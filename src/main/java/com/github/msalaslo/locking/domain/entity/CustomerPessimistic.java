package com.github.msalaslo.locking.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CustomerPessimistic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer-pessimistic-sequence-generator")
    @GenericGenerator(
    	      name = "customer-pessimistic-sequence-generator",
    	      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
    	      parameters = {
    	        @Parameter(name = "sequence_name", value = "CUSTOMER_PESSIMISTIC_SEQ"),
    	        @Parameter(name = "initial_value", value = "1"),
    	        @Parameter(name = "increment_size", value = "1")
    	        }
    	    )
    private long id;

	public String firstName;
	public String lastName;
	
	@Version
	Long version;
}
