package com.jspiders.springrestproject.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspiders.springrestproject.pojo.User;
import com.jspiders.springrestproject.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User addUser(User user) {
		return userRepository.addUser(user);
	}

	public User updateUser(User user) {
		return userRepository.updateUser(user);
	}

	public User deleteUser(int userId) {

		return userRepository.deleteUser(userId);
	}

	public User validateUser(String email, String password) {
		try {
			return userRepository.validateUser(email, password);
		} catch (NoResultException e) {
			return null;
		}

	}

	public User updateCarsForUser(int carId, int userId) {

		return userRepository.updateCarsForUser(carId, userId);
	}
}
