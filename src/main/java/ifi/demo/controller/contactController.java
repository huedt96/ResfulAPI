package ifi.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ifi.demo.ErrorType;
import ifi.demo.model.*;
import ifi.demo.repository.contactRepository;
import ifi.demo.repository.customerRepository;

@Controller
@RequestMapping(path = "/ServiceCalls/contact")
public class contactController {

	public static final Logger logger = LoggerFactory.getLogger(contactController.class);

	@Autowired
	private contactRepository contactrepository;
	@Autowired
	private customerRepository customerrepository;

	@PostMapping(path = "/add")
	public ResponseEntity<?> createContact(@RequestParam int cusid, @RequestParam String name, @RequestParam String job,
			@RequestParam String detail) {
		Contacts contacts = new Contacts();
		contacts.setContactName(name);
		contacts.setJobTitle(job);
		contacts.setContactDetails(detail);
		Customers cus1 = customerrepository.findByCustomerId(cusid);
		if (cus1 == null) {
			logger.error("Customer with id {} not found.", cusid);
			return new ResponseEntity<Object>(new ErrorType("Customer with id " + cusid + " not found"),
					HttpStatus.NOT_FOUND);
		}
		cus1.setCustomerId(cusid);
		contacts.setCustomers(cus1);

		contactrepository.save(contacts);
		return new ResponseEntity<Contacts>(contacts, HttpStatus.OK);
	}

	@GetMapping(path = "/get/all")
	public @ResponseBody Iterable<Contacts> getAllContacts() {
		return contactrepository.findAll();
	}

	@GetMapping(path = "/get/{id}")
	public @ResponseBody ResponseEntity<?> getByContactId(@PathVariable int id) {
		Contacts con = contactrepository.findByContactId(id);
		if (con == null) {
			logger.error("Contact with id {} not found.", id);
			return new ResponseEntity<Object>(new ErrorType("Contact with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Contacts>(con, HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete/{id}")
	public @ResponseBody ResponseEntity<?> deleteContact(@PathVariable int id) {
		Contacts con = contactrepository.findByContactId(id);
		if (con == null) {
			logger.error("Contact with id {} not found.", id);
			return new ResponseEntity<Object>(new ErrorType("Contact with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		contactrepository.delete(id);
		return new ResponseEntity<Contacts>(HttpStatus.NO_CONTENT);

	}

	@PutMapping(path = "/update/{id}")
	public @ResponseBody ResponseEntity<?> updateContact(@PathVariable int id, @RequestParam int cusid,
			@RequestParam String name, @RequestParam String job, @RequestParam String detail) {
		Contacts currentCon = contactrepository.findByContactId(id);
		if (currentCon == null) {
			logger.error("Contact with id {} not found.", id);
			return new ResponseEntity<Object>(new ErrorType("Contact with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}

		currentCon.setContactName(name);
		currentCon.setJobTitle(job);
		currentCon.setContactDetails(detail);
		Customers cus1 = customerrepository.findByCustomerId(cusid);
		if (cus1 == null) {
			logger.error("Customer with id {} not found.", cusid);
			return new ResponseEntity<Object>(new ErrorType("Customer with id " + cusid + " not found"),
					HttpStatus.NOT_FOUND);
		}
		cus1.setCustomerId(cusid);
		currentCon.setCustomers(cus1);

		contactrepository.save(currentCon);
		return new ResponseEntity<Contacts>(currentCon, HttpStatus.OK);

	}
}
