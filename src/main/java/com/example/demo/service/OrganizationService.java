package com.example.demo.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.EmilAlreadyExistException;
import com.example.demo.exception.PhoneNumberAlreadyExistException;
import com.example.demo.exception.WebsiteAlreadyExistException;
import com.example.demo.model.Organization;
import com.example.demo.respository.OrganizationRepository;

@Service
public class OrganizationService {

	private static final Logger logger = LoggerFactory.getLogger(OrganizationService.class);
	@Autowired
	private OrganizationRepository organizationRepository;

	@Transactional
	public Organization createOrganization(@Valid Organization org) {
		// TODO Auto-generated method stub
		try {

			Organization orgEmail = organizationRepository.getOrgByEmail(org.getEmail());
			Organization orgPhone = organizationRepository.getOrgByPhone(org.getPhoneNumber());
			Organization orgWebsite = organizationRepository.getOrgByWebsite(org.getWebsite());

			logger.info("*************************** Email " + orgEmail);
			logger.info("**************************** Phone " + orgPhone);
			logger.info(" ***************************Website" + orgWebsite);

			if (orgEmail != null && orgPhone != null && orgWebsite != null) {
				throw new EmilAlreadyExistException(
						"Exception Caught :- All three email , phoneNumber and website already exist in the database");
			}
			if (orgEmail != null && orgPhone != null) {
				throw new EmilAlreadyExistException("Exception Caught:-  Email and PhoneNumber already exist in the database");
			}

			if (orgEmail != null && orgWebsite != null) {
				throw new EmilAlreadyExistException(" Exception Caught:- Email and Website already exist in the database");
			}

			if (orgPhone != null && orgWebsite != null) {
				throw new EmilAlreadyExistException("Exception Caught:- Phone and Website already exist in the database");
			}

			if (orgEmail != null) {
				throw new EmilAlreadyExistException(" Exception :- Duplicate Email Id Found In The DB");
			}

			if (orgPhone != null) {
				throw new PhoneNumberAlreadyExistException("Exception Caught:- TelephoneNumber already Exist");
			}
			if (orgWebsite != null) {
				throw new WebsiteAlreadyExistException(
						" Exception Caught:- Website " + org.getWebsite() + "  " + " already Exsit in the DB");
			}
			Organization orgToDB = organizationRepository.save(org);
			return orgToDB;
		} catch (final EmilAlreadyExistException ex) {
			logger.error("********************************************");
			throw new EmilAlreadyExistException(ex.getMessage());
		} catch (final WebsiteAlreadyExistException ex) {
			logger.error("******************Website Exception********************");
			throw new WebsiteAlreadyExistException(
					" Exception Caught:- Website " + org.getWebsite() + "  " + " already Exsit in the DB");
		} catch (final PhoneNumberAlreadyExistException ex) {
			logger.error("*********Exception PhoneNumber Exception************************");
			throw new PhoneNumberAlreadyExistException("Exception Caught:- TelephoneNumber already Exist");
		} finally {
			logger.info(" *************End of createOrganization method of class OrganizationService**************");
		}
	}

}
