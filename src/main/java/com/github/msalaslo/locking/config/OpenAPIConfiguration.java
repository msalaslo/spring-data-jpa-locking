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
        		.group("no-locking")
        		.addOpenApiCustomiser(new VersionedApi(contextPath, "/no-locking"))        	
                .build();
    }
    
    @Bean
    public GroupedOpenApi v10OptimisticApi() {
        return GroupedOpenApi.builder()
        		.group("optimistic")
        		.addOpenApiCustomiser(new VersionedApi(contextPath, "/optimistic"))        	
                .build();
    }
    
    @Bean
    public GroupedOpenApi v10PessimisticApi() {
        return GroupedOpenApi.builder()
        		.group("pessimistic")
        		.addOpenApiCustomiser(new VersionedApi(contextPath, "/pessimistic"))        	
                .build();
    }
    
}
