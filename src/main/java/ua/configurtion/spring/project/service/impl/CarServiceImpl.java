package ua.configurtion.spring.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.configurtion.spring.project.entity.Car;
import ua.configurtion.spring.project.repository.CarRepository;
import ua.configurtion.spring.project.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository; 
	
	@Override
	public void saveCar(Car car) {
		carRepository.save(car);
		
	}

	@Override
	public List<Car> findAllCar() {
	
		return carRepository.findAll();
	}

	@Override
	public Car findCarById(int id) {
	
		return carRepository.findOne(id);
	}

}
