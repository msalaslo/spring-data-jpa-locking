package com.github.msalaslo.locking.config;

import javax.validation.constraints.NotNull;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenApiConfiguration configuration.  API documentation is available at /swagger-ui.html
 *
 * @since 1.0.0
 * @author [https://github.com/msalaslo]
 */
@Configuration
public class OpenAPIConfiguration {

    @NotNull
    @Value("${server.servlet.context-path}")
    private String contextPath;
    
    @Bean
    public GroupedOpenApi v10Api() {
        return GroupedOpenApi.builder()
        		.group("v1.0")
        		.addOpenApiCustomiser(new VersionedApi(contextPath, "/v1.0"))        	
                .build();
    }
    
}
