package com.github.msalaslo.locking.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
/**
 * For optimistic locking we have to define a Version field annotated with @Version
 * @author Miguel
 *
 */
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer-sequence-generator")
    @GenericGenerator(
    	      name = "customer-sequence-generator",
    	      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
    	      parameters = {
    	        @Parameter(name = "sequence_name", value = "CUSTOMER_SEQ"),
    	        @Parameter(name = "initial_value", value = "1"),
    	        @Parameter(name = "increment_size", value = "1")
    	        }
    	    )
    private long id;

	public String firstName;
	public String lastName;
	
}
