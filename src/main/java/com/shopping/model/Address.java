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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Override
	public int hashCode() {
		return Objects.hash(buildingName, city, country, pincode, state, streetNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(buildingName, other.buildingName) && Objects.equals(city, other.city)
				&& Objects.equals(country, other.country) && Objects.equals(pincode, other.pincode)
				&& Objects.equals(state, other.state) && Objects.equals(streetNo, other.streetNo);
	}



}
