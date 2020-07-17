package com.parkingapp.park.service;

import java.util.List;

import com.parkingapp.park.model.Car;
import com.parkingapp.park.model.User;

public interface ParkService {

	User saveUser(User user);
    User fetchUserByEmail(String email);
	User fetchUserByEmailAndPassword(String tempemail, String temppassword);
	List<Car> activeSlot(int active);
	List<Car> getAllCar();
	List<Car> addNewCar(Car car);
	List<Car> removeSlot(int id);
	List<Car> getCarByColor(String color);
	Car getByRegisterNumber(String registerdNumber);
	int setParkingSlot(int slot);
	
}
