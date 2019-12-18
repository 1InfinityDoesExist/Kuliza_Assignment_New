package com.example.demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Organization;
import com.example.demo.respository.OrganizationRepository;
import com.example.demo.service.MapStateToError;
import com.example.demo.service.OrganizationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/object/org")
@Api(value = "/api/object/org", description = "Organization Operations")
public class OrganizationController {

	private static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

	@Autowired
	private OrganizationRepository organizationRepository;
	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private MapStateToError mapStateToError;

	@PostMapping(path = "/create")
	public ResponseEntity<?> storeOrganizationDetials(@Valid @RequestBody Organization org,
			BindingResult bindingResult) {
		
		ResponseEntity<?> errorMap = mapStateToError.errorMapState(bindingResult);
		if (errorMap != null) {
			return errorMap;
		}
		Organization orgToDB1 = organizationService.createOrganization(org);

		logger.info("Organization log - " + orgToDB1);
		//System.out.println("coming");
//		System.out.println(organizationRepository.findById(orgToDB.getId()));
		logger.info("Coming");
		return new ResponseEntity<Organization>(orgToDB1, HttpStatus.OK);

	}

}
