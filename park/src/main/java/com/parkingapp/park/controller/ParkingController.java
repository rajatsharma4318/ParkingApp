package com.parkingapp.park.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.parkingapp.park.model.Car;
import com.parkingapp.park.model.User;
import com.parkingapp.park.service.ParkService;
import com.parkingapp.park.serviceimp.ParkServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ParkingController {

	@Autowired
	ParkService parkService;
	

	@PostMapping("/save-user")
	public User registerUser(@RequestBody User user) {
		String tempid = user.getEmail();
		if (tempid != null) {
			User fetchuser = parkService.fetchUserByEmail(tempid);
			if (fetchuser != null) {
				throw new RuntimeException("User with" + tempid + "already register");
			}

		}
		User newuser = null;
		newuser = parkService.saveUser(user);
		return newuser;
	}

	@PostMapping("/login")
	public User loginUser(@RequestBody User user) {
		User usercheck = null;
		String tempemail = user.getEmail();
		String temppassword = user.getPassword();
		if (tempemail != null && temppassword != null) {
			usercheck = parkService.fetchUserByEmailAndPassword(tempemail, temppassword);
			if (usercheck == null) {
				throw new RuntimeException("Not Found");
			}
		}
		return usercheck;
	}

	@PutMapping("/bookslot")
	public List<Car> bookCarSlot(@RequestBody Car car) {
		
		ParkServiceImpl park=new ParkServiceImpl();
		List<Car> newCarList = null;
		List<Car> cars = parkService.activeSlot(1);
		if (cars.size() < park.getSlot()) {
			System.out.println(park.getSlot());
			newCarList = parkService.addNewCar(car);
		} else {
			System.out.println(park.getSlot());
			throw new RuntimeException("All slot is booked");
		}
		return newCarList;
	}
	
	
	@GetMapping("/remove-slot/{id}")
	public List<Car> removeSlot(@PathVariable int id)
	{
	  return parkService.removeSlot(id);
	}
	
	
	@GetMapping("/get-car-by-color/{color}")
	public List<Car> getAllCarByColor(@PathVariable String color)
	{
		List<Car> cars= parkService.getCarByColor(color);
		if(cars.size()==0)
		{
			throw new RuntimeException("Color not Found");
		}
		return cars;
	}
	
	
	@GetMapping("/get-carslot-by-register/{registerdNumber}")
	public Car getCarByRegisterNumber(@PathVariable String registerdNumber)
	{
		Car car=parkService.getByRegisterNumber(registerdNumber);
		if(car==null)
		{
		 throw new RuntimeException("data not found");
		}
		return car;
	}
	
	@GetMapping("/get-all-parking")
	public List<Car> getAllParking()
	{
		return parkService.activeSlot(1);
	}
	
	
	@GetMapping("/set-parking-slot/{slot}")
	public int setSlot(@PathVariable int slot)
	{
		return parkService.setParkingSlot(slot);
	}
	
	

}
