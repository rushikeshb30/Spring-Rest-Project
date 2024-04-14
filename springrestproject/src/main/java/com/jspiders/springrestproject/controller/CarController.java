package com.jspiders.springrestproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.springrestproject.pojo.Car;
import com.jspiders.springrestproject.response.Response;
import com.jspiders.springrestproject.service.CarService;

import lombok.Delegate;

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

	@PutMapping("/car")
	public ResponseEntity<Response<Car>> updateCar(@RequestBody Car car) {

		Car updatedCar = carService.updateCar(car);
		Response<Car> response = new Response<Car>();
		if (updatedCar != null) {
			response.setMessage("Car Updated.");
			response.setData(updatedCar);
			response.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<Response<Car>>(response, HttpStatus.OK);
		} else {
			response.setMessage("Car not found.");
			response.setData(updatedCar);
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Response<Car>>(response, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/car")
	public ResponseEntity<Response<Car>> deleteCar(@RequestParam(name = "carId") int carId) {

		Car deletedCar = carService.deleteCar(carId);
		Response<Car> response = new Response<Car>();
		if (deletedCar != null) {
			response.setMessage("Car deleted.");
			response.setData(deletedCar);
			response.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<Response<Car>>(response, HttpStatus.OK);
		} else {
			response.setMessage("Car Not Found.");
			response.setData(deletedCar);
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Response<Car>>(response, HttpStatus.NOT_FOUND);
		}
	}
}
