package ua.configurtion.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.configurtion.spring.project.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{

}
