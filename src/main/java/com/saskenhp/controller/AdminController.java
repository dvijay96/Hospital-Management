package com.saskenhp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saskenhp.entity.Employee;
import com.saskenhp.entity.Role;
import com.saskenhp.mappings.Admin;
import com.saskenhp.repositories.AppointmentRepo;
import com.saskenhp.repositories.EmployeeRepo;
import com.saskenhp.repositories.RoleRepo;

@RestController
@RequestMapping(Admin.reqURL)
public class AdminController {

	@Autowired
	private EmployeeRepo repo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private AppointmentRepo apptRepo;

	@PostMapping("/addAdmin")
	public ResponseEntity<Employee> addAdmin(@RequestBody Employee emp) {
		try {
			repo.save(emp);
			return new ResponseEntity<>(emp, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(emp, HttpStatus.CONFLICT);
		}
	}

	@PostMapping(Admin.addDoc)
	public ResponseEntity<Employee> saveDoctor(@RequestBody Employee emp) {
		return addAdmin(emp);
	}

	@PutMapping(Admin.editDoc)
	public ResponseEntity<Employee> editDoctor(@RequestBody Employee emp) {
		return addAdmin(emp);
	}

	@DeleteMapping(Admin.deleteDoc)
	public ResponseEntity<?> deleteDoctor(@PathVariable int id) {

		Employee emp = repo.findById(id).get();
		if (emp != null) {
			Role role = emp.getRole();
			roleRepo.delete(role);
			repo.delete(emp);
			return new ResponseEntity<String>("Doctor " + id + " deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Doctor doesn't exists", HttpStatus.NOT_FOUND);
	}

	@GetMapping(Admin.docByName)
	public ResponseEntity<Employee> findDoctor(@PathVariable String name) {
		Employee emp = repo.findByName(name);
		if (emp != null)
			return new ResponseEntity<Employee>(emp, HttpStatus.FOUND);
		return new ResponseEntity<Employee>(emp, HttpStatus.NOT_FOUND);
	}

	@GetMapping(Admin.listDoc)
	public ResponseEntity<List<Employee>> doctorsList() {
		return new ResponseEntity<List<Employee>>(repo.findAllDoctors(), HttpStatus.FOUND);
	}
}
