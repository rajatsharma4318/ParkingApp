package com.parkingapp.park.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.parkingapp.park.model.Car;

public interface CarRepo extends JpaRepository<Car, Number>{

	
	@Query("FROM Car where UPPER(status)=?#{[0]}")
	List<Car> activeSlotStatus(int active);

	List<Car> findByColor(String color);

	Car findByRegisternumber(String registerdNumber);
}
