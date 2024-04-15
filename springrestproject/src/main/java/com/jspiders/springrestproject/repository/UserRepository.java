package com.jspiders.springrestproject.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.jspiders.springrestproject.pojo.Car;
import com.jspiders.springrestproject.pojo.User;

@Repository
public class UserRepository {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	public void openConnection() {
		entityManagerFactory = Persistence.createEntityManagerFactory("car");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}

	public void closeConnection() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
		if (entityManager != null) {
			entityManager.close();
		}
		if (entityTransaction != null) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public User addUser(User user) {
		openConnection();
		User userToBeChecked = user;
		Query query = entityManager.createQuery("Select user from User user");
		List<User> users = query.getResultList();
		for (User user1 : users) {
			if (user1.getEmail().equals(userToBeChecked.getEmail())) {
				userToBeChecked = null;
				break;
			}
		}
		if (userToBeChecked != null) {
			entityTransaction.begin();
			entityManager.persist(userToBeChecked);
			entityTransaction.commit();
			closeConnection();
		}
		return userToBeChecked;
	}

	public User updateUser(User user) {
		openConnection();
		User userToBeUpdated = entityManager.find(User.class, user.getId());
		if (userToBeUpdated != null) {
			userToBeUpdated.setName(user.getName());
			userToBeUpdated.setEmail(user.getEmail());
			userToBeUpdated.setMobile(user.getMobile());
			userToBeUpdated.setPassword(user.getPassword());
			entityTransaction.begin();
			entityManager.persist(userToBeUpdated);
			entityTransaction.commit();
			closeConnection();
			return userToBeUpdated;

		} else {
			return userToBeUpdated;
		}
	}

	public User deleteUser(int userId) {

		openConnection();
		User userToBeDeleted = entityManager.find(User.class, userId);
		if (userToBeDeleted != null) {
			entityTransaction.begin();
			entityManager.remove(userToBeDeleted);
			entityTransaction.commit();
			closeConnection();
			return userToBeDeleted;
		} else {
			return userToBeDeleted;
		}
	}

	public User validateUser(String email, String password) {

		openConnection();
		Query query = entityManager.createQuery("Select user from User user where email = ?1 and password = ?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		User user = (User) query.getSingleResult();
		closeConnection();
		return user;
	}

	public User updateCarsForUser(int carId, int userId) {

		openConnection();
		User user = entityManager.find(User.class, userId);
		Car car = entityManager.find(Car.class, carId);
		if (user != null && car != null) {
			List<Car> cars = user.getCars();
			cars.add(car);
			entityTransaction.begin();
			entityManager.persist(user);
			entityTransaction.commit();
			closeConnection();
			return user;
		} else {
			return null;
		}
	}

}
