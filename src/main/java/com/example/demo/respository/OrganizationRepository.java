package com.example.demo.respository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long>, CrudRepository<Organization, Long> {

	@Transactional
	@Modifying
	@Query("Update Organization set isDeleted = true where id=?1")
	public void deleteOrganizationByID(Long id);

	@Query("Select Organization from #{#entityName} Organization where id=?1 and isDeleted = ?2")
	public Organization getOrganizationById(Long id, boolean b);

	@Query("Select Organization from #{#entityName} Organization where isDeleted = ?1")
	public List<Organization> getAllOrganization(boolean b);

	@Query("Select Organization from #{#entityName} Organization where email=?1 and phoneNumber = ?2 and website=?3 and isDeleted=?4")
	public Organization duplicateCheck(String email, String phoneNumber, String website, boolean b);

	// @Query("Select Organization from #{#entityName} Organization where isDeleted
	// = false and email=?1 and phoneNumber = ?2 and website=?3")
	// public Organization duplicateCheck(String email, String phoneNumber, String
	// website);

}
