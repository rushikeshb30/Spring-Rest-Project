package com.jspiders.springrestproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.springrestproject.pojo.Car;
import com.jspiders.springrestproject.response.Response;
import com.jspiders.springrestproject.service.CarService;

@RestController
public class CarController {

	@Autowired
	private CarService carService;

	@PostMapping("/car")
	public ResponseEntity<Response<Car>> addCar(@RequestBody Car car) {

		Car addedCar = carService.addCar(car);
		Response<Car> response = new Response<Car>();
		if (addedCar != null) {
			response.setMessage("Car Added.");
			response.setData(addedCar);
			response.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<Response<Car>>(response, HttpStatus.OK);
		} else {
			response.setMessage("Car Not Added.");
			response.setData(addedCar);
			response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
			return new ResponseEntity<Response<Car>>(response, HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
