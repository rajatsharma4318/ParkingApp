package com.parkingapp.park.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingapp.park.model.Car;
import com.parkingapp.park.model.User;
import com.parkingapp.park.repo.CarRepo;
import com.parkingapp.park.repo.UserRepo;
import com.parkingapp.park.service.ParkService;

@Service
public class ParkServiceImpl implements ParkService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	CarRepo carRepo;

	public static int slot;
	
	 
	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		ParkServiceImpl.slot = slot;
	}
	
	
	
	

	@Override
	public User saveUser(User user) {

		userRepo.save(user);
		return user;
	}

	public User fetchUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public User fetchUserByEmailAndPassword(String tempemail, String temppassword) {
	
		return userRepo.findByEmailAndPassword(tempemail, temppassword);
	}

	@Override
	public List<Car> activeSlot(int active) {
		return carRepo.activeSlotStatus(active);
	}

	@Override
	public List<Car> getAllCar() {

		return carRepo.findAll();
	}

	@Override
	public List<Car> addNewCar(Car car) {

		List<Car> carList = carRepo.findAll();

		if (carList.size() < slot) {
			car.setStatus(1);
			carRepo.save(car);
		}

		else if (carList.size() == slot) {
			for (Car cars : carList) {
				if (cars.getStatus() == 0) {
					car.setId(cars.getId());
					car.setStatus(1);
					carRepo.save(car);
					break;
				}
			}
		}
		return carRepo.findAll();
	}

	@Override
	public List<Car> removeSlot(int id) {
	    
		Car getCat=carRepo.findById(id).get();
		getCat.setColor("");
		getCat.setRegisternumber("");
		getCat.setStatus(0);
		carRepo.save(getCat);
		return carRepo.findAll();
	}

	@Override
	public List<Car> getCarByColor(String color) {
		
		List<Car> carByColor=carRepo.findByColor(color);
		return carByColor;
	}

	@Override
	public Car getByRegisterNumber(String registerdNumber) {
		Car carSlot=carRepo.findByRegisternumber(registerdNumber);
		return carSlot;
	}

	@Override
	public int setParkingSlot(int slot) {
		this.slot=slot;
		System.out.println(slot+" size"+getSlot());
		return slot;
	}
	
	
	
	

}
