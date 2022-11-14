package com.shopping.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Validated
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;

	private String streetNo;

	private String buildingName;

	@NotEmpty
	@NotNull
	@NotBlank
	private String city;

	@NotEmpty
	@NotNull
	@NotBlank
	private String state;

	@NotEmpty
	@NotNull
	@NotBlank
	private String country;

	private String pincode;

	@JsonIgnore
	@JoinColumn(name = "customer_id")
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Address other = (Address) obj;
		return Objects.equals(buildingName, other.buildingName) && Objects.equals(city, other.city)
				&& Objects.equals(country, other.country) && Objects.equals(pincode, other.pincode)
				&& Objects.equals(state, other.state) && Objects.equals(streetNo, other.streetNo);
	}

	public Integer getAddressId() {
		return addressId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getPincode() {
		return pincode;
	}

	public String getState() {
		return state;
	}

	public String getStreetNo() {
		return streetNo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(buildingName, city, country, pincode, state, streetNo);
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

}
