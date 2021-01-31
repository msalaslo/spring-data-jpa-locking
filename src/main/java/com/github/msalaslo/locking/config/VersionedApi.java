package com.github.msalaslo.locking.config;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.customizers.OpenApiCustomiser;

import io.swagger.v3.core.filter.SpecFilter;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.slf4j.Slf4j;

public class VersionedApi extends SpecFilter implements OpenApiCustomiser {
    
    private final String contextPath;
    private final String version;
    
    public VersionedApi(String contextPath, String version) {
        this.contextPath = contextPath;
        this.version = version;
    }
    
    @Override
    public void customise(OpenAPI openApi) {
    	//Overwrite the generated absolute URL with a relative URL to allow requests through gateways
    	// Adds the version to the context path
    	List<Server> serverList = new ArrayList<Server>();
    	serverList.add(new Server().url(contextPath + version));
		openApi.setServers(serverList);
		
		openApi.setOpenapi("3.0.1");
		
		//Add authentication option
		Components components = openApi.getComponents();
		if(components == null) {
			components = new Components();
		}
		openApi.setComponents(components.addSecuritySchemes("basicScheme",
				new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")));
		
		//API information
		openApi.setInfo(
				new Info()
				.title("CUSTOMER_CRUD")
				.description("Simple Customer CRUD Rest API documentation.")
				.version(version)
				.contact(new Contact()
						.email("msalaslo@gmail.com")
						.name("Miguel")
						.url("https://github.com/msalaslo"))
				.license(new License().
						name("License of API for Miguel")
						.url("https://github.com/msalaslo")));

		//Remove the version from the api definition paths
		Paths versionedPaths = new Paths();
        openApi.getPaths().entrySet().stream().filter(path -> path.getKey().startsWith(version)).forEach(path -> {
        	String key = path.getKey().substring(version.length());
            versionedPaths.put(key,path.getValue());
        });
        openApi.setPaths(versionedPaths);
        
        super.removeBrokenReferenceDefinitions(openApi);
    }
    
}
