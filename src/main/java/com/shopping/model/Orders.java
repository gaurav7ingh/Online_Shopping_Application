package com.shopping.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;

	@NotBlank
	@NotNull
	@NotEmpty
	private LocalDate orderDate;

	@NotBlank
	@NotNull
	@NotEmpty
	private String orderStatus;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;


	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;

}
