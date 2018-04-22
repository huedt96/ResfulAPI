package ifi.demo.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ifi.demo.repository.customerRepository;
import ifi.demo.ErrorType;
import ifi.demo.model.*;

@Controller
@RequestMapping(path="/ServiceCalls/customer")
public class customerController {

	public static final Logger logger = LoggerFactory.getLogger(contactController.class);
	
	@Autowired
	private customerRepository customerrepository;
	
	
	@PostMapping(path ="/add")
	public @ResponseBody String addCustomer (
			@RequestParam String name, 
			@RequestParam String address, 
			@RequestParam String detail) {
		Customers cus = new Customers();
		cus.setCustomerName(name);
		cus.setCustomerAddress(address);
		cus.setOtherDetails(detail);
		customerrepository.save(cus);
		return "Add Successfully";
	}
	
	@GetMapping(path="/get/all")
	public @ResponseBody Iterable<Customers> getAllCustomers() {
		return customerrepository.findAll();
	}
	
	@GetMapping(path ="/get/{id}")
	public @ResponseBody ResponseEntity<?> getByCustomerId (@PathVariable int id){
		Customers cus = customerrepository.findByCustomerId(id);
		if(cus == null) {
			logger.error("Customer with id {} not found.", id);
			return new ResponseEntity<>(new ErrorType("Customer with id " + id + " not found"), HttpStatus.NOT_FOUND);
				}
		return new ResponseEntity<Customers>(cus, HttpStatus.OK);		
	}
	
	@DeleteMapping(path="/delete/{id}")
	public @ResponseBody ResponseEntity<?> deleteCustomer (@PathVariable int id) {
		Customers cus = customerrepository.findByCustomerId(id);
		if(cus == null) {
			logger.error("Customer with id {} not found.", id);
			return new ResponseEntity<>(new ErrorType("Customer with id " + id + " not found"), HttpStatus.NOT_FOUND);
				}
		customerrepository.delete(id);
		return new ResponseEntity<Customers>(HttpStatus.NO_CONTENT);
		
	}
	
	@PutMapping(path ="/update/{id}")
	public @ResponseBody ResponseEntity<?> updateCustomer (
			@PathVariable int id, 
			@RequestParam String name,
			@RequestParam String address, 
			@RequestParam String detail) {
		Customers currentCus = customerrepository.findByCustomerId(id);
		if(currentCus == null) {
			logger.error("Customer with id {} not found.", id);
			return new ResponseEntity<Object>(new ErrorType("Customer with id " + id + " not found"), HttpStatus.NOT_FOUND);
				}
		currentCus.setCustomerName(name);
		currentCus.setCustomerAddress(address);
		currentCus.setOtherDetails(detail);
		
		customerrepository.save(currentCus);
		return new ResponseEntity<Customers>(currentCus, HttpStatus.OK);
		
	}

}
