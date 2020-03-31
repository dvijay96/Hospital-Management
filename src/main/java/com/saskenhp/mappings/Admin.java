package com.saskenhp.mappings;

public interface Admin {

	String reqURL = "/admin/secured/rest";

	String addDoc = "/hp/addDoc";
	String editDoc = "/hp/editDoc";
	String deleteDoc = "/hp/deleteDoc/{id}";
	String docByName = "hp/doc/{name}";
	String listDoc = "/hp/allDocs";

	String addRecp = "/hp/recp/add";
	String editRecp = "hp/recp/edit";
	String deleteRecp = "hp/recp/delete/{id}";
	String recpByName = "hp/recp/{name}";
	String listRecp = "hp/resp/all";

	String addPatient = "/hp/patient/add";
	String editPatient = "hp/patient/edit";
	String deletePatient = "hp/patient/delete/{id}";
	String patientByName = "hp/patient/{name}";
	String listPatient = "hp/patient/all";

}
