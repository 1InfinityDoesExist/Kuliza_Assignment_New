package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

	@Query("Select Organization from #{#entityName} Organization where phoneNumber = ?1 and isDeleted=false")
	public Organization getOrgByPhone(String phoneNumber);

	@Query("Select Organization from #{#entityName} Organization where email = ?1 and isDeleted=false")
	public Organization getOrgByEmail(String email);

	@Query("Select Organization from #{#entityName} Organization where website = ?1 and isDeleted=false")
	public Organization getOrgByWebsite(String website);

}
