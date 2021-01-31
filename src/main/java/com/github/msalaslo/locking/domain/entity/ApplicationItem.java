package com.github.msalaslo.locking.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Sample domain entity. <b>Please remove for actual project implementation.</b>
 *
 * @since 1.0.0
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationItem implements Serializable {

  private static final long serialVersionUID = 1L;

  private String itemCode;

  private String itemDescription;
    
}
