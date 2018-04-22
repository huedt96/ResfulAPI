package ifi.demo.repository;


import org.springframework.data.repository.CrudRepository;

import ifi.demo.model.*;

public interface customerRepository extends CrudRepository<Customers, Integer>{

	public Customers findByCustomerId(Integer id);

	Customers findByCustomerName(String name);

	static void updateCustomer(Customers currentCus) {		
	}
	
}
