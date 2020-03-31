package com.saskenhp.mappings;

public interface Receptionists {

	String reqUrl = "/recep/secured/rest";

	String addPatient = "/hp/patient/add";
	String editPatient = "hp/patient/edit";
	String deletePatient = "hp/patient/delete";
	String patientByName = "hp/patient/{name}";
	String listPatient = "hp/patient/all";

	String addAppt = "/hp/Appt/add";
	String editAppt = "hp/Appt/edit";
	String deleteAppt = "hp/Appt/delete";
	String ApptOfDoc = "hp/Appt/{name}";
	String listAppts = "hp/Appt/all";

}
