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

import com.saskenhp.entity.Doctor;
import com.saskenhp.entity.Employee;
import com.saskenhp.entity.Patient;
import com.saskenhp.entity.Role;
import com.saskenhp.repositories.DoctorRepo;
import com.saskenhp.repositories.EmployeeRepo;
import com.saskenhp.repositories.PatientRepo;
import com.saskenhp.requesturls.Admin;

@RestController
@RequestMapping(Admin.reqURL)
public class AdminController {

	@Autowired
	private EmployeeRepo repo;

//	@Autowired
//	private RoleRepo roleRepo;

	@Autowired
	private PatientRepo patRepo;

	@Autowired
	private DoctorRepo docRepo;

	@PostMapping(Admin.addEmp)
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp) {
		try {

			Role role = emp.getRole();

			if (role.getRole().equals("DOC")) {

				Doctor doc = docRepo.findById(emp.getEmpId()).get();

				if (doc == null) {
					doc = new Doctor();
					doc.setDocId(emp.getEmpId());
					doc.setFirstName(emp.getFirstName());
					doc.setLastName(emp.getLastName());

				} else {

					if (!doc.getFirstName().equals(emp.getFirstName())) {
						doc.setFirstName(emp.getFirstName());
					} else if (!doc.getLastName().equals(emp.getLastName())) {
						doc.setLastName(emp.getLastName());
					}
				}
				docRepo.save(doc);
			}
			repo.save(emp);
			return new ResponseEntity<>(emp, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(emp, HttpStatus.CONFLICT);
		}
	}

	@PutMapping(Admin.editEmp)
	public ResponseEntity<Employee> editDoctor(@RequestBody Employee emp) {
		return addEmployee(emp);
	}

	@DeleteMapping(Admin.deleteEmp)
	public ResponseEntity<?> deleteDoctor(@PathVariable int id) {

		Employee emp = repo.findById(id).get();
		if (emp != null) {
//			Role role = emp.getRole();
//			roleRepo.delete(role);
			repo.delete(emp);
			return new ResponseEntity<String>("Doctor " + id + " deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Doctor doesn't exists", HttpStatus.NOT_FOUND);
	}

	@GetMapping(Admin.docByName)
	public ResponseEntity<Employee> findDoctor(@PathVariable String name) {
		Employee emp = repo.findByName(name, "DOC");
		if (emp != null)
			return new ResponseEntity<Employee>(emp, HttpStatus.FOUND);
		return new ResponseEntity<Employee>(emp, HttpStatus.NOT_FOUND);
	}

	@GetMapping(Admin.listAll)
	public ResponseEntity<List<Employee>> getAll() {
		return new ResponseEntity<List<Employee>>(repo.findAll(), HttpStatus.FOUND);

	}

	@GetMapping(Admin.listDoc)
	public ResponseEntity<List<Employee>> doctorsList() {
		return new ResponseEntity<List<Employee>>(repo.findAll("DOC"), HttpStatus.FOUND);
	}

	// Receptionist's control

	@GetMapping(Admin.listRecp)
	public ResponseEntity<List<Employee>> recepList() {
		return new ResponseEntity<List<Employee>>(repo.findAll("RECEP"), HttpStatus.FOUND);
	}

	@GetMapping(Admin.recpByName)
	public ResponseEntity<Employee> getRecepByName(@PathVariable String name) {
		return new ResponseEntity<Employee>(repo.findByName(name, "RECEP"), HttpStatus.FOUND);
	}

	// Patient control

	@PostMapping(Admin.addPatient)
	public ResponseEntity<Patient> addPatient(@RequestBody Patient p) {
		try {
			patRepo.save(p);
			return new ResponseEntity<Patient>(p, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Patient>(HttpStatus.CONFLICT);
		}
	}

	@PutMapping(Admin.editPatient)
	public String editPatient(@RequestBody Patient p) {
		try {
			patRepo.save(p);
			return p.getPatientName() + " edited sucessfully";
		} catch (Exception e) {
			return "Wrong details conflict";
		}
	}

	@DeleteMapping(Admin.deletePatient)
	public String deletePatient(@PathVariable int id) {
		Patient p = patRepo.findById(id).get();
		patRepo.delete(p);
		return p.getPatientName() + " deleted";
	}

	@GetMapping(Admin.patientByName)
	public ResponseEntity<?> getPatient(@PathVariable String name) {
		Patient p = patRepo.findByName(name);
		if (p != null)
			return new ResponseEntity<Patient>(p, HttpStatus.FOUND);
		return new ResponseEntity<String>("Patient doesn't exists", HttpStatus.NOT_FOUND);
	}

	@GetMapping(Admin.listPatient)
	public ResponseEntity<List<Patient>> patientsList() {
		return new ResponseEntity<List<Patient>>(patRepo.findAll(), HttpStatus.FOUND);
	}
}
