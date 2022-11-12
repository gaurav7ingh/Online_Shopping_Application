package com.shopping.service;

import java.util.Set;

import com.shopping.exception.AddressException;
import com.shopping.exception.CustomerException;
import com.shopping.model.Address;

public interface AddressService {
	public Address addAddress(Address a) throws AddressException, CustomerException;

	public Address updateAddress(Address a) throws AddressException;

	public Set<Address> viewAllAddress(Integer id) throws CustomerException;

	public Address removeAddress(Address a) throws AddressException;

	public Address viewAddress(Address a) throws AddressException;

}
