package com.repen.cardatabase.web;

import com.repen.cardatabase.domain.Car;
import com.repen.cardatabase.domain.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
	@Autowired
	private CarRepository carRepository;

	@RequestMapping("/cars")
	public Iterable<Car> getCars() {
		return carRepository.findAll();
	}
}
