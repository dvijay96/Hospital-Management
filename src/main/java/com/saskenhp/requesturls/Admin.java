package com.saskenhp.requesturls;

public interface Admin {

	String reqURL = "/secured/admin/rest";

	String addEmp = "/hp/addEmp";

	String editEmp = "/hp/editEmp";

	String deleteEmp = "/hp/deleteEmp/{id}";

	String listAll = "/hp/getall";

	// DOCTOR Service

	String docByName = "hp/doc/{name}";

	String listDoc = "/hp/allDocs";

	// RECEP Service

	String recpByName = "hp/recp/{name}";
	String listRecp = "hp/recp/all";

	// Patient Service

	String addPatient = "/hp/addPatient";
	String editPatient = "hp/patient/edit";
	String deletePatient = "hp/patient/delete/{id}";
	String patientByName = "hp/patient/{name}";
	String listPatient = "hp/patient/all";

}
