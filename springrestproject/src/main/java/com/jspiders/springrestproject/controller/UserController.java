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

import com.jspiders.springrestproject.pojo.User;
import com.jspiders.springrestproject.response.Response;
import com.jspiders.springrestproject.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public ResponseEntity<Response<User>> addUser(@RequestBody User user) {

		User addedUser = userService.addUser(user);
		Response<User> response = new Response<User>();
		if (addedUser != null) {
			response.setMessage("User Added.");
			response.setData(addedUser);
			response.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<Response<User>>(response, HttpStatus.OK);
		} else {
			response.setMessage("User already exists.");
			response.setData(addedUser);
			response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
			return new ResponseEntity<Response<User>>(response, HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/user")
	public ResponseEntity<Response<User>> updateUser(@RequestBody User user) {

		User updatedUser = userService.updateUser(user);
		Response<User> response = new Response<User>();

		if (updatedUser != null) {
			response.setMessage("User Updated.");
			response.setData(updatedUser);
			response.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<Response<User>>(response, HttpStatus.FOUND);
		} else {
			response.setMessage("User not found.");
			response.setData(updatedUser);
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Response<User>>(response, HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/user")
	public ResponseEntity<Response<User>> deleteUser(@RequestParam(name = "userId") int userId) {

		User deletedUser = userService.deleteUser(userId);
		Response<User> response = new Response<User>();
		if (deletedUser != null) {
			response.setMessage("User deleted.");
			response.setData(deletedUser);
			response.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<Response<User>>(response, HttpStatus.OK);
		} else {
			response.setMessage("User Not Found.");
			response.setData(deletedUser);
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Response<User>>(response, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/useremail")
	public ResponseEntity<Response<User>> validateUser(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) {

		User validatedUser = userService.validateUser(email, password);
		Response<User> response = new Response<User>();
		if (validatedUser != null) {
			response.setMessage("Logged in successfully.");
			response.setData(validatedUser);
			response.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<Response<User>>(response, HttpStatus.FOUND);
		} else {
			response.setMessage("Invalid email or password.");
			response.setData(validatedUser);
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Response<User>>(response, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/usermobile")
	public ResponseEntity<Response<User>> validateUser(@RequestParam(name = "mobile") long mobile,
			@RequestParam(name = "password") String password) {

		User validatedUser = userService.validateUser(mobile, password);
		Response<User> response = new Response<User>();
		if (validatedUser != null) {
			response.setMessage("Logged in successfully.");
			response.setData(validatedUser);
			response.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<Response<User>>(response, HttpStatus.FOUND);
		} else {
			response.setMessage("Invalid email or password.");
			response.setData(validatedUser);
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Response<User>>(response, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/usercars")
	public ResponseEntity<Response<User>> updateCarsForUser(@RequestParam(name = "carId") int carId,
			@RequestParam(name = "userId") int userId) {

		User updatedUser = userService.updateCarsForUser(carId, userId);
		Response<User> response = new Response<User>();
		if (updatedUser != null) {
			response.setMessage("Car list updated for User.");
			response.setData(updatedUser);
			response.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<Response<User>>(response, HttpStatus.OK);
		} else {
			response.setMessage("User or Car not found.");
			response.setData(updatedUser);
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Response<User>>(response, HttpStatus.NOT_FOUND);
		}
	}
}
