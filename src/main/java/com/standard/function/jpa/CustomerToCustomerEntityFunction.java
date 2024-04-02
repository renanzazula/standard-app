package com.standard.function.jpa;

import com.standard.domain.Customer;
import com.standard.entity.CustomerEntity;

import java.util.function.Function;

public class CustomerToCustomerEntityFunction implements Function<CustomerEntity, Customer> {

	@Override
	public Customer apply(CustomerEntity input) {
		Customer output = new Customer();
		output.setId(input.getId());
		return output;
	}

}
