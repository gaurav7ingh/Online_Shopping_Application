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
	public Address createAddress(Address a, Integer id) throws AddressException, CustomerException {
		Optional<Customer> customer = customerDao.findById(id);
		if (customer == null)
			throw new CustomerException("Please provide the currect customer Id...!");

		Set<Address> addresses = customer.get().getAddresses();
		if (addresses.contains(a))
			throw new AddressException("Address is already mentioned");
		addresses.add(a);
		a.setCustomer(customer.get());

		Address address = addressDao.save(a);
		if (address == null)
			throw new AddressException("Address Not saved yet...!");

		return address;
	}

	@Override
	public Address removeAddress(Address a) throws AddressException {
		Address add = addressDao.findById(a.getAddressId())
				.orElseThrow(() -> new AddressException("Address Not found for deleting"));

		if (add == null)
			throw new AddressException("Address Not found for deleting");

		addressDao.delete(add);

		return add;

	}

	@Override
	public Address updateAddress(Address a) throws AddressException {
		Optional<Address> optional = addressDao.findById(a.getAddressId());

		if (!optional.isPresent())
			throw new AddressException("This address does't exists");

		Customer customer = optional.get().getCustomer();
		a.setCustomer(customer);
		Address address = addressDao.save(a);

		return address;
	}

	@Override
	public Address viewAddress(Integer a) throws AddressException {
		Optional<Address> add = addressDao.findById(a);
		if (!add.isPresent())
			throw new AddressException("No address exits for the same");

		Address address = add.get();
		System.out.println(address);

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

}
