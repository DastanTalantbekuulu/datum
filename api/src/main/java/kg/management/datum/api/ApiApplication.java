package kg.management.datum.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "kg.management.datum.domain")
@EntityScan(basePackages = "kg.management.datum.domain.entity")
@SpringBootApplication
public class ApiApplication {
    static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
