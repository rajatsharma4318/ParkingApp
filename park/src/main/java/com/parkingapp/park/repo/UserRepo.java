package com.parkingapp.park.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkingapp.park.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	
	public User findByEmail(String email);

	public User findByEmailAndPassword(String tempemail, String temppassword);
}
