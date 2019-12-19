package com.example.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BaseException;
import com.example.demo.model.Organization;
import com.example.demo.respository.OrganizationRepository;

@Service
public class OrganizationService {

	private static final Logger logger = LoggerFactory.getLogger(OrganizationService.class);
	@Autowired
	private OrganizationRepository organizationRepository;

	public Organization createOrganization(Organization org) {
		// TODO Auto-generated method stub
		try {

			Organization duplicateCheck = organizationRepository.duplicateCheck(org.getEmail(), org.getPhoneNumber(),
					org.getWebsite());
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

}
