package com.example.demo.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.model.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "Organization")
@Table(name = "organization", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "email", "phone_number", "website" }) })
@ApiModel(value = "organizaiton", description = "Stores All The Details Of The Organization")
@EntityListeners(AuditingEntityListener.class)
@TypeDefs({ @TypeDef(name = "AddressDetailsType", typeClass = AddressDetailsType.class) })
public class Organization extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "organization_name")
	@ApiModelProperty(notes = "Name of the Organization")
	private String organizationName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "date_of_est")
	@ApiModelProperty(notes = "Date OF Establishment of the Organization")
	private Date est;

	@Column(name = "is_private")
	private boolean isPrivate;

	@Column(name = "address", columnDefinition = "jsonb", nullable = false, updatable = true, unique = false)
	@Type(type = "AddressDetailsType")
	@ApiModelProperty(notes = "Containes Info About The Address of the Organization")
	private Address address;

	@Column(name = "no_of_employee")
	@ApiModelProperty(notes = "No of Employee in the Organization")
	private Long noOfEmployee;

	@Column(name = "email", unique = true, nullable = false, updatable = true)
	@NotBlank(message = "Email Field Can't Be Blank")
	@NotEmpty(message = " Email Field Must Not Be Empty")
	private String email;

	@Column(name = "phone_number", unique = true, nullable = false, updatable = true)
	@NotBlank(message = "PhoneNumber Field Can't Be Blank")
	@NotEmpty(message = " PhoneNumbe Field Must Not Be Empty")
	@ApiModelProperty(notes = "PhoneNumber of the Organization")
	private String phoneNumber;

	@Column(name = "website", unique = true, nullable = false, updatable = true)
	@NotBlank(message = "Website Field Can't Be Blank")
	@NotEmpty(message = " Website Field Must Not Be Empty")
	@ApiModelProperty(notes = " Website of the Organizatiion")
	private String website;

	public Organization() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Organization(Long id, boolean isDeleted, LocalDateTime creationDate, LocalDateTime modificationDate) {
		super(id, isDeleted, creationDate, modificationDate);
		// TODO Auto-generated constructor stub
	}

	public Organization(String organizationName, Date est, boolean isPrivate, Address address, Long noOfEmployee,
			@NotBlank(message = "Email Field Can't Be Blank") @NotEmpty(message = " Email Field Must Not Be Empty") String email,
			@NotBlank(message = "PhoneNumber Field Can't Be Blank") @NotEmpty(message = " PhoneNumbe Field Must Not Be Empty") String phoneNumber,
			@NotBlank(message = "Website Field Can't Be Blank") @NotEmpty(message = " Website Field Must Not Be Empty") String website) {
		super();
		this.organizationName = organizationName;
		this.est = est;
		this.isPrivate = isPrivate;
		this.address = address;
		this.noOfEmployee = noOfEmployee;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.website = website;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Date getEst() {
		return est;
	}

	public void setEst(Date est) {
		this.est = est;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getNoOfEmployee() {
		return noOfEmployee;
	}

	public void setNoOfEmployee(Long noOfEmployee) {
		this.noOfEmployee = noOfEmployee;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((est == null) ? 0 : est.hashCode());
		result = prime * result + (isPrivate ? 1231 : 1237);
		result = prime * result + ((noOfEmployee == null) ? 0 : noOfEmployee.hashCode());
		result = prime * result + ((organizationName == null) ? 0 : organizationName.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Organization other = (Organization) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (est == null) {
			if (other.est != null)
				return false;
		} else if (!est.equals(other.est))
			return false;
		if (isPrivate != other.isPrivate)
			return false;
		if (noOfEmployee == null) {
			if (other.noOfEmployee != null)
				return false;
		} else if (!noOfEmployee.equals(other.noOfEmployee))
			return false;
		if (organizationName == null) {
			if (other.organizationName != null)
				return false;
		} else if (!organizationName.equals(other.organizationName))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Organization [organizationName=" + organizationName + ", est=" + est + ", isPrivate=" + isPrivate
				+ ", address=" + address + ", noOfEmployee=" + noOfEmployee + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", website=" + website + "]";
	}

}
