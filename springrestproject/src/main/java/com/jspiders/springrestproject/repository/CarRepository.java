package com.jspiders.springrestproject.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.jspiders.springrestproject.pojo.Car;

@Repository
public class CarRepository {

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

	public Car addCar(Car car) {

		openConnection();
		entityTransaction.begin();
		entityManager.persist(car);
		entityTransaction.commit();
		closeConnection();
		return car;
	}

	public Car updateCar(Car car) {

		openConnection();
		Car carToBeUpdated = entityManager.find(Car.class, car.getId());
		if (carToBeUpdated != null) {
			carToBeUpdated.setName(car.getName());
			carToBeUpdated.setBrand(car.getBrand());
			carToBeUpdated.setPrice(car.getPrice());
			carToBeUpdated.setColor(car.getColor());
			entityTransaction.begin();
			entityManager.persist(carToBeUpdated);
			entityTransaction.commit();
			closeConnection();
			return carToBeUpdated;
		} else {
			return carToBeUpdated;
		}
	}

	public Car deleteCar(int carId) {

		openConnection();
		Car carToBeDeleted = entityManager.find(Car.class, carId);
		if (carToBeDeleted != null) {
			entityTransaction.begin();
			entityManager.remove(carToBeDeleted);
			entityTransaction.commit();
			closeConnection();
			return carToBeDeleted;
		} else {
			return carToBeDeleted;
		}
	}

}
