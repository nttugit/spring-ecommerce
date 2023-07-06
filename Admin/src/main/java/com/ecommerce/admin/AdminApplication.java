package com.ecommerce.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = {"com.ecommerce.library.*", "com.ecommerce.admin.*"})@EnableJpaRepositories(value = "com.ecommerce.library.repository")
@EntityScan(value="com.ecommerce.library.model")
@Configuration
public class AdminApplication  implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

}
