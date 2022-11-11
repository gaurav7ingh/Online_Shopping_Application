package com.shopping.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.exception.AddressException;
import com.shopping.model.Address;
import com.shopping.repository.AddressRepo;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepo aRepo;

	@Override
	public Address createAddress(Address a) throws AddressException {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public Address updateAddress(Address a) throws AddressException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address viewAllAddress(String id) throws AddressException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address removeAddress(Address a) throws AddressException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address viewAddress(Address a) throws AddressException {
		// TODO Auto-generated method stub
		return null;
	}

}
