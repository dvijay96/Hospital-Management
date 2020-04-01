package com.saskenhp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.saskenhp.entity.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {

	@Query("from Doctor where firstName=:name")
	public Doctor findByName(String name);
}
