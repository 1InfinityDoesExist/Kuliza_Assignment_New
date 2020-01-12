package com.example.demo.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BaseException;
import com.example.demo.model.Address;
import com.example.demo.model.Organization;
import com.example.demo.respository.OrganizationRepository;
import com.example.demo.utility.ReflectionUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class OrganizationService {

	private static final Logger logger = LoggerFactory.getLogger(OrganizationService.class);

	ReflectionUtil refUtil = ReflectionUtil.getInstance();

	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	public void setUp() {
		objectMapper.registerModule(new JavaTimeModule());
	}

	@Autowired
	private OrganizationRepository organizationRepository;

	public Organization createOrganization(Organization org) {
		// TODO Auto-generated method stub
		try {

			Organization duplicateCheck = organizationRepository.duplicateCheck(org.getEmail(), org.getPhoneNumber(),
					org.getWebsite(), false);
			if (duplicateCheck != null) {
				throw new BaseException("Duplicate Value Found");
			}
			Organization persistedOrg = organizationRepository.save(org);
			return persistedOrg;

		} catch (final BaseException ex) {
			throw new BaseException(ex.getMessage());
		}
	}

	// To be asked
	public Organization getOrganizationByID(Long id) {
		try {
			Organization org = organizationRepository.getOrganizationById(id, false);
			return org;
		} catch (final Exception ex) {
			throw new BaseException("Sorry Error Retrieving Data From DB");
		}
	}

	public List<Organization> getAllOrganization() {
		// TODO Auto-generated method stub
		try {
			List<Organization> orgList = organizationRepository.getAllOrganization(false);
			logger.info(" logger : " + orgList);
			return orgList;
		} catch (final Exception ex) {
			throw new BaseException("Sorry Could Not Retrieve Data From The Server");
		}
	}

	public String deleteOrgByID(Long id) {
		// TODO Auto-generated method stub
		try {
			organizationRepository.deleteOrganizationByID(id);// what if the isDeletedFlag is not updated
			throw new BaseException("Database Connect Broke");
			// return "Success";
		} catch (final BaseException ex) {
			throw new BaseException("Sorry Could Not Delete Data From The Server");
		}
	}

	public Organization updateOrganization(String organization, Long id)
			throws JsonMappingException, JsonProcessingException, ParseException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		Organization organizationFromDB = getOrganizationByID(id);
		if (organizationFromDB == null) {
			throw new BaseException("Sorry No Data Found For This Id:" + id);
		}
		JSONParser parser = new JSONParser();
		Organization orgFromPayload = objectMapper.readValue(organization, Organization.class);

		try {
			JSONObject obj = (JSONObject) parser.parse(organization);
			for (Iterator iterator = ((Map<String, String>) obj).keySet().iterator(); iterator.hasNext();) {
				String propName = (String) iterator.next();
				if (propName.equals("address")) {
					if (obj.get("address") != null) {
						JSONObject addObject = (JSONObject) obj.get("address");
						if (organizationFromDB.getAddress() == null) {
							organizationFromDB.setAddress(new Address());
						}
						for (Object src : addObject.keySet()) {
							String addPropName = (String) src;
							refUtil.getSetterMethod("Address", addPropName).invoke(organizationFromDB.getAddress(),
									addObject.get(addPropName)); 
						}
					} else {
						organizationFromDB.setAddress(null);
					}

				} else {
					refUtil.getSetterMethod("Organization", propName).invoke(organizationFromDB, obj.get(propName));
				}
			}

		} catch (final BaseException ex) {
			logger.info(ex.getMessage());
		}

		Organization updatedOrg = organizationRepository.save(organizationFromDB);
		return updatedOrg;
	}

}
