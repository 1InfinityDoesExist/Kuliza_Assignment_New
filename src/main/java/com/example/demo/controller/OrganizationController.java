package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Organization;
import com.example.demo.service.MapStateToError;
import com.example.demo.service.OrganizationService;
import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path = "/api/object/org")
@Api(value = "/api/object/org", description = "Organization Crud Operation")
public class OrganizationController {

	private static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private MapStateToError mapStateToError;

	@RequestMapping(path = "/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Create Organization Resource", notes = "Create Crud Operation", response = Organization.class)
	public ResponseEntity<?> storeOrganizationDetials(@Valid @RequestBody Organization org,
			BindingResult bindingResult) {
		ResponseEntity<?> errorMap = mapStateToError.errorMapState(bindingResult);
		if (errorMap != null) {
			return errorMap;
		}
		Organization orgToDB1 = organizationService.createOrganization(org);
		logger.info(" ****************************Got Persisted In The Database************************");
		Gson gson = new Gson();
		String jsonInString = gson.toJson(orgToDB1);
		return new ResponseEntity<String>(jsonInString, HttpStatus.OK);
	}

	@RequestMapping(path = "/get/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Retrieve Organization Resource By ID", notes = "Retrieve Organization Details By ID", response = Organization.class)
	public ResponseEntity<?> getOrganizationById(
			@ApiParam(value = "id", required = true) @PathVariable(value = "id") Long id) {

		logger.info("**********************Inside Organization Get Method******************************");
		Organization orgFromDB = organizationService.getOrganizationByID(id);
		if (orgFromDB == null) {
			return new ResponseEntity<String>("Sorry No Data Found", HttpStatus.BAD_REQUEST);
		}
		Gson gson = new Gson();
		String jsonString = gson.toJson(orgFromDB);
		return new ResponseEntity<String>(jsonString, HttpStatus.OK);
	}

	@RequestMapping(path = "/get", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Retrieve All Organization Resource", notes = "Retrieve All organization From The DB", response = Organization.class, responseContainer = "LIST")
	public ResponseEntity<?> getAllOrganizationDetails() {
		List<Organization> orgListFromDB = organizationService.getAllOrganization();
		if (orgListFromDB.size() == 0 || orgListFromDB == null) {
			return new ResponseEntity<String>("Sorry Not Data Found ", HttpStatus.BAD_REQUEST);
		}

		Gson gson = new Gson();
		String gsonString = gson.toJson(orgListFromDB);
		return new ResponseEntity<String>(gsonString, HttpStatus.OK);
	}

	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE, produces = "text/plain")
	@ResponseBody
	@ApiOperation(value = "Remove Organization Resource By ID", notes = "Remove Organization From The DB Using Specific Id", response = String.class)
	public ResponseEntity<?> deleteOrganizationById(
			@ApiParam(value = "id", required = true) @PathVariable(value = "id") Long id) {

		ResponseEntity<?> responseString = getOrganizationById(id);
		String org = (String) responseString.getBody();
		if (org.equals("Sorry No Data Found")) {
			return new ResponseEntity<String>("Sorry Org with id:- " + id + " does Not Exist", HttpStatus.BAD_REQUEST);
		}
		logger.info("org value:-" + org);
		Organization orgFromDB = new Gson().fromJson(org, Organization.class);
		logger.info("orgFromDB:-" + orgFromDB);
		// No need check for orgFromDB over here

		String response = organizationService.deleteOrgByID(id);
		if (response == null) {
			return new ResponseEntity<String>(" Sorry Could Not Remove Org Resource From The Data", HttpStatus.OK);
		}
		return new ResponseEntity<String>("SuccessFully Deleted Organization with ID:- " + id, HttpStatus.OK);

	}

}
