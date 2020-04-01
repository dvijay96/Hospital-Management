package com.saskenhp.controller;

import java.util.List;
import java.util.Set;

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

import com.saskenhp.entity.Appointment;
import com.saskenhp.entity.Doctor;
import com.saskenhp.entity.Patient;
import com.saskenhp.repositories.AppointmentRepo;
import com.saskenhp.repositories.DoctorRepo;
import com.saskenhp.repositories.PatientRepo;
import com.saskenhp.requesturls.Recep;

@RestController
@RequestMapping(Recep.reqUrl)
public class RecepController {

	@Autowired
	private AppointmentRepo apptRepo;

	@Autowired
	private PatientRepo patRepo;

	@Autowired
	private DoctorRepo docRepo;

	@PostMapping(Recep.addPatient)
	public ResponseEntity<?> addPatient(@RequestBody Patient p) {
		try {
			patRepo.save(p);
			return new ResponseEntity<Patient>(p, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Wrong Input", HttpStatus.CONFLICT);
		}
	}

	@PutMapping(Recep.editPatient)
	public ResponseEntity<?> editPatient(@RequestBody Patient p) {
		return addPatient(p);
	}

	@DeleteMapping(Recep.deletePatient)
	public String deletePatient(@PathVariable int id) {
		Patient p = getPatientById(id);
		patRepo.delete(p);
		return "Patient " + p.getPatientName() + " deleted";
	}

	@GetMapping(Recep.patientById)
	public Patient getPatientById(@PathVariable int id) {
		return patRepo.findById(id).get();
	}

	@GetMapping(Recep.listPatient)
	public List<Patient> getAllPatients() {
		return patRepo.findAll();
	}

	@PostMapping(Recep.addAppt)
	public ResponseEntity<?> createAppt(@RequestBody Appointment appt) {
		try {
			Doctor doc = docRepo.findByName(appt.getDoctorName().split(" ")[0]);
			Patient p = patRepo.findByName(appt.getFirstName());
			doc.getPatients().add(p);
			doc.getAppointments().add(appt);
			apptRepo.save(appt);
			return new ResponseEntity<Appointment>(appt, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Wrong input", HttpStatus.CONFLICT);
		}
	}

	@PutMapping(Recep.editAppt)
	public String editAppt(@RequestBody Appointment appt) {
		try {
			Appointment previousAppt = apptRepo.findById(appt.getAppointmentId()).get();

			if (!previousAppt.getDoctorName().equals(appt.getDoctorName())) {

				Doctor newDoc = docRepo.findByName(appt.getDoctorName().split(" ")[0]);
				Patient p = patRepo.findByName(appt.getFirstName());

				Doctor preDoc = docRepo.findByName(previousAppt.getDoctorName().split(" ")[0]);
				preDoc.getPatients().remove(p);

				newDoc.getPatients().add(p);
				newDoc.getAppointments().add(previousAppt);

			} else if (!previousAppt.getAppointmentDate().equals(appt.getAppointmentDate())) {
				previousAppt.setAppointmentDate(appt.getAppointmentDate());
			}
			apptRepo.save(previousAppt);
			return "appointement updated";
		} catch (Exception e) {
			return "wrong  appt input";
		}
	}

	@DeleteMapping(Recep.deleteAppt)
	public String deleteAppt(@PathVariable int id) {

		Appointment appt = apptRepo.findById(id).get();
		Doctor doc = docRepo.findByName(appt.getDoctorName().split(" ")[0]);
		Patient p = patRepo.findByName(appt.getFirstName());

		doc.getPatients().remove(p);

		apptRepo.delete(appt);

		return "appointment deleted";
	}

	@GetMapping(Recep.ApptOfDoc)
	public ResponseEntity<Set<Appointment>> docsAppt(@PathVariable String name) {

		Doctor doc = docRepo.findByName(name);
		return new ResponseEntity<Set<Appointment>>(doc.getAppointments(), HttpStatus.FOUND);
	}

	@GetMapping(Recep.listAppts)
	public ResponseEntity<List<Appointment>> getAll() {
		return new ResponseEntity<List<Appointment>>(apptRepo.findAll(), HttpStatus.FOUND);
	}
}
