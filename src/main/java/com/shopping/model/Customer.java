package com.shopping.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	@Size(min = 3, max = 20, message = "firstname should be greater then 3 and less the 20")
	private String firstName;
	@Size(min = 2, max = 20, message = "lastname should be greater then 2 and less the 20")
	private String lastName;
	@Size(min = 10, max = 10, message = "the number shold be 10 digits only")
	private String MobileNumber;
	@Size(min = 13, message = "email should be greater then 13")
	private String email;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private Set<Address> addresses = new HashSet<>();

	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart = new Cart();

	@OneToMany(cascade = CascadeType.ALL)
	private List<Orders> orders;

}
