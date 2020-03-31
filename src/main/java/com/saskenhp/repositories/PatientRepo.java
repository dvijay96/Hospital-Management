package com.saskenhp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saskenhp.entity.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {

}
