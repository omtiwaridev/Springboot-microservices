package com.ezybytes.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(title = "Loans microservices REST API Documentation",
        description = "EasyBank Loans microservices REST API Documentation",
        version = "v1",
        contact = @Contact(
                name = "Sahil Bhagirathi",
                email = "sahil@gmail.com",
                url = "http://www.eazybytes.com"
        ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.eazybytes.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "EazyBank Loans microservice REST API Documentation",
                url = "http://localhost:8085/swagger-ui/index.html#/loans-controller/updateLoanDetails"
        )

)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
