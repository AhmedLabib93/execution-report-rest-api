package com.springboot.execution.report.taskexecutionreportrestapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI customOpenAPI() {

		return new OpenAPI().components(new Components())
				.info(new Info().title("REST Execution Report Api Documentation").version("1.0")
						.license(swaggerLicense()).contact(swaggerContact()));
	}

	private Contact swaggerContact() {
		Contact petclinicContact = new Contact();
		petclinicContact.setName("Ahmed Labib");
		petclinicContact.setEmail("db.ahmed93@gmail.com");
		petclinicContact.setUrl("https://github.com/AhmedLabib93/execution-report-rest-api");
		return petclinicContact;
	}

	private License swaggerLicense() {
		License petClinicLicense = new License();
		petClinicLicense.setName("Apache 2.0");
		petClinicLicense.setUrl("http://www.apache.org/licenses/LICENSE-2.0");
		petClinicLicense.setExtensions(Collections.emptyMap());
		return petClinicLicense;
	}

}