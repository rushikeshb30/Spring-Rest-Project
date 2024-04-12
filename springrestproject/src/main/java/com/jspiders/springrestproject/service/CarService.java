package com.jspiders.springrestproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspiders.springrestproject.pojo.Car;
import com.jspiders.springrestproject.repository.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	public Car addCar(Car car) {

		return carRepository.addCar(car);
	}
}
