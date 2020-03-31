package com.saskenhp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saskenhp.entity.Appointment;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

	public void update(Appointment appt);
}
