package com.saskenhp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saskenhp.repositories.AppointmentRepo;
import com.saskenhp.repositories.PatientRepo;

@RestController
@RequestMapping("")
public class DoctorController {

	@Autowired
	private AppointmentRepo apptRepo;
	
	@Autowired
	private PatientRepo patRepo;
	
	
}
