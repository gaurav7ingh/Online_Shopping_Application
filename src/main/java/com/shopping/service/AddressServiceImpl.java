package com.shopping.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.exception.AddressException;
import com.shopping.exception.CustomerException;
import com.shopping.model.Address;
import com.shopping.model.Customer;
import com.shopping.repository.AddressDao;
import com.shopping.repository.CustomerRepo;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private CustomerRepo customerDao;

	@Override
	public Address createAddress(Address a) throws AddressException {
		Customer c = a.getCustomer();
		if (c.getAddresses().contains(a))
			throw new AddressException("This Address already exists");

		c.getAddresses().add(a);

		Address address = addressDao.save(a);

		return address;
	}

	@Override
	public Address updateAddress(Address a) throws AddressException {
		Optional<Address> optional = addressDao.findById(a.getAddressId());

		if (!optional.isPresent())
			throw new AddressException("This address does't exists");

		Address address = addressDao.save(a);

		return address;
	}

	@Override
	public Set<Address> viewAllAddress(Integer id) throws CustomerException {
		Optional<Customer> optional = customerDao.findById(id);
		if (!optional.isPresent())
			throw new CustomerException("No user exist with this id");

		Customer c = optional.get();
		return c.getAddresses();
	}

	@Override
	public Address removeAddress(Address a) throws AddressException {
		Address add = addressDao.findById(a.getAddressId()).get();
		if (add == null)
			throw new AddressException("Address Not found for deleting");

		addressDao.delete(add);

		return add;

	}

	@Override
	public Address viewAddress(Address a) throws AddressException {
		Optional<Address> add = addressDao.findById(a.getAddressId());
		if (!add.isPresent())
			throw new AddressException("No address exits for the same");

		return add.get();
	}

}
