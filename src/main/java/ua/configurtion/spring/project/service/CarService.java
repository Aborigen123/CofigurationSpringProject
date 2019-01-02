package ua.configurtion.spring.project.service;

import java.util.List;

import ua.configurtion.spring.project.entity.Car;

public interface CarService {

	void saveCar(Car car);
	
	List<Car> findAllCar();
	
	Car findCarById(int id);
	
}
