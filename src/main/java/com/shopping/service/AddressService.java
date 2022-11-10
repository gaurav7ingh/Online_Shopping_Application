package com.shopping.service;


public interface AddressService {
	public Address createAddress(Address a) throws AddressException;

	public Address updateAddress(Address a) throws AddressException;

	public Address viewAllAddress(String id) throws AddressException;

	public Address removeAddress(Address a) throws AddressException;

	public Address viewAddress(Address a) throws AddressException;

}
