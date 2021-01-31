package com.github.msalaslo.locking.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customer DTO object
 *
 * @since 1.0.0
 * 
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO extends BaseDTO {
	
    @Schema(description = "Customer ID")
    public String id;
    
    @Schema(description = "Customer first name", required = true)
    public String firstName;
    
    @Schema(description = "Customer last name", required = true)
    public String lastName;

}
