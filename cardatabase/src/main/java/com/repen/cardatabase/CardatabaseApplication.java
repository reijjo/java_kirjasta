package com.repen.cardatabase;

import com.repen.cardatabase.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"service", "com.repen.cardatabase"})
public class CardatabaseApplication {
//	Repositories
	@Autowired
	private CarRepository carRepository;

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	UserRepository userRepository;

//	Logger
	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

//	MAIN
	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Hello Spring Boot!");
	}
	@Bean
	CommandLineRunner runner() {
		return args -> {
			// Save demo to database
			Owner owner1 = new Owner("John", "Johnson");
			Owner owner2 = new Owner("Mary", "Robinson");
			ownerRepository.save(owner1);
			ownerRepository.save(owner2);

			carRepository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000, owner1));
			carRepository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2014, 29000, owner2));
			carRepository.save(new Car("Toyota", "Prius", "Silver", "KKO-0120", 2018, 39000, owner2));

			userRepository.save(new User("user", "$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi", "USER"));
			userRepository.save(new User("admin", "$2a$12$yOA5oXRRnI4e9q3qxIMFdeo3wOAHDvfrnNZiCsx9hYX0oI5RjW8ge", "ADMIN"));
		};
	}
}
