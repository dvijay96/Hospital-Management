package com.saskenhp.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saskenhp.entity.Appointment;
import com.saskenhp.entity.Doctor;
import com.saskenhp.entity.Patient;
import com.saskenhp.repositories.DoctorRepo;
import com.saskenhp.requesturls.DoctorsReq;

@RestController
@RequestMapping(DoctorsReq.reqUrl)
public class DoctorController {

	@Autowired
	private DoctorRepo docRepo;

	@GetMapping(DoctorsReq.listPatients)
	public ResponseEntity<Set<Patient>> patientsList(@PathVariable int id) {
		Doctor doc = docRepo.findById(id).get();
		return new ResponseEntity<Set<Patient>>(doc.getPatients(), HttpStatus.FOUND);
	}

	@GetMapping(DoctorsReq.listAppts)
	public ResponseEntity<Set<Appointment>> appointmentList(@PathVariable int id) {
		Doctor doc = docRepo.findById(id).get();
		return new ResponseEntity<Set<Appointment>>(doc.getAppointments(), HttpStatus.FOUND);
	}
}
