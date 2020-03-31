package com.saskenhp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saskenhp.entity.Employee;
import com.saskenhp.mappings.Admin;
import com.saskenhp.repositories.EmployeeRepo;

@RestController
@RequestMapping(Admin.reqURL)
public class AdminController {

	@Autowired
	private EmployeeRepo repo;

	@PostMapping("/addAdmin")
	public ResponseEntity<Employee> addAdmin(@RequestBody Employee emp) {
		try {
			repo.save(emp);
			return new ResponseEntity<>(emp, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(emp, HttpStatus.CONTINUE);
		}
	}

}
