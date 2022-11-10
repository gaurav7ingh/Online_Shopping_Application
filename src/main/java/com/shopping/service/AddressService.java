package com.shopping.service;

import com.shopping.exception.AddressException;
import com.shopping.model.Address;

public interface AddressService {
	public Address createAddress(Address a) throws AddressException;

	public Address updateAddress(Address a) throws AddressException;

	public Address viewAllAddress(String id) throws AddressException;

	public Address removeAddress(Address a) throws AddressException;

	public Address viewAddress(Address a) throws AddressException;

}
