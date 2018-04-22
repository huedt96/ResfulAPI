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

import ifi.demo.*;
import ifi.demo.model.ChargeCodes;
import ifi.demo.model.Contacts;
import ifi.demo.model.Problems;
import ifi.demo.model.ServiceRequestCalls;
import ifi.demo.model.StatusCodes;
import ifi.demo.repository.chargecodeRepository;
import ifi.demo.repository.contactRepository;
import ifi.demo.repository.problemRepository;
import ifi.demo.repository.serviceRcRepository;
import ifi.demo.repository.statuscodeRepository;

@Controller
@RequestMapping(path = "/ServiceCalls/srcalls")
public class ServiceRequestCallController {

	public static final Logger logger = LoggerFactory.getLogger(ServiceRequestCallController.class);
	@Autowired
	private serviceRcRepository srcRepository;
	@Autowired
	private contactRepository contactrepository;
	@Autowired
	private chargecodeRepository chargecoderepository;
	@Autowired
	private problemRepository problemrepository;
	@Autowired
	private statuscodeRepository statuscoderepository;

	@PostMapping(path = "/add")
	public @ResponseBody ResponseEntity<?> addSRCalls(
			@RequestParam String charcode, 
			@RequestParam int contid,
			@RequestParam String probcode, 
			@RequestParam String statcode, 
			@RequestParam String datetime,
			@RequestParam String detail) {
		ServiceRequestCalls src = new ServiceRequestCalls();
		src.setDateTimeOfCall(datetime);
		src.setOtherDetails(detail);

		ChargeCodes char1 = chargecoderepository.findByChargeCode(charcode);
		if (char1 == null) {
			logger.error("ChargeCode with code {} not found.", charcode);
			return new ResponseEntity<>(new ErrorType("ChargeCode with code " + charcode + " not found"),
					HttpStatus.NOT_FOUND);
		}
		char1.setChargeCode(charcode);
		src.setChargeCodes(char1);

		Contacts con1 = contactrepository.findByContactId(contid);
		if (con1 == null) {
			logger.error("Contact with id {} not found.", contid);
			return new ResponseEntity<Object>(new ErrorType("Contact with id " + contid + " not found"),
					HttpStatus.NOT_FOUND);
		}
		con1.setContactId(contid);
		src.setContacts(con1);

		StatusCodes sta1 = statuscoderepository.findByStatusCode(statcode);
		if (sta1 == null) {
			logger.error("StatusCode with code {} not found.", statcode);
			return new ResponseEntity<>(new ErrorType("StatusCode with code " + statcode + " not found"),
					HttpStatus.NOT_FOUND);
		}
		sta1.setStatusCode(statcode);
		src.setStatusCodes(sta1);

		Problems pro1 = problemrepository.findByProblemCode(probcode);
		if (pro1 == null) {
			logger.error("Problem with code {} not found.", probcode);
			return new ResponseEntity<>(new ErrorType("Problem with code " + probcode + " not found"),
					HttpStatus.NOT_FOUND);
		}
		pro1.setProblemCode(probcode);
		src.setProblems(pro1);

		srcRepository.save(src);
		return new ResponseEntity<ServiceRequestCalls>(src, HttpStatus.OK);
	}

	@GetMapping(path = "/get/all")
	public @ResponseBody Iterable<ServiceRequestCalls> getAll() {
		return srcRepository.findAll();
	}

	@GetMapping(path = "/get/{id}")
	public @ResponseBody ResponseEntity<?> getId(@PathVariable int id) {
		ServiceRequestCalls src = srcRepository.findByCallId(id);
		if (src == null) {
			logger.error("ServiceRequestCall with id {} not found.", id);
			return new ResponseEntity<>(new ErrorType("ServiceRequestCall with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ServiceRequestCalls>(src, HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete/{id}")
	public @ResponseBody ResponseEntity<?> deleteServiceRequestCall(@PathVariable int id) {
		ServiceRequestCalls src = srcRepository.findByCallId(id);
		if (src == null) {
			logger.error("ServiceRequestCall with id {} not found.", id);
			return new ResponseEntity<>(new ErrorType("ServiceRequestCall with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		srcRepository.delete(id);
		return new ResponseEntity<ServiceRequestCalls>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(path = "/update/{id}")
	public @ResponseBody ResponseEntity<?> updateSRC(
			@PathVariable int id, 
			@RequestParam String charcode,
			@RequestParam int contid, 
			@RequestParam String probcode, 
			@RequestParam String statcode,
			@RequestParam String datetime, 
			@RequestParam String detail) {

		ServiceRequestCalls src = srcRepository.findByCallId(id);
		if (src == null) {
			logger.error("ServiceRequestCall with id {} not found.", id);
			return new ResponseEntity<>(new ErrorType("ServiceRequestCall with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		src.setDateTimeOfCall(datetime);
		src.setOtherDetails(detail);

		ChargeCodes char1 = chargecoderepository.findByChargeCode(charcode);
		if (char1 == null) {
			logger.error("ChargeCode with code {} not found.", charcode);
			return new ResponseEntity<>(new ErrorType("ChargeCode with code " + charcode + " not found"),
					HttpStatus.NOT_FOUND);
		}
		char1.setChargeCode(charcode);
		src.setChargeCodes(char1);

		Contacts con1 = contactrepository.findByContactId(contid);
		if (con1 == null) {
			logger.error("Contact with id {} not found.", contid);
			return new ResponseEntity<Object>(new ErrorType("Contact with id " + contid + " not found"),
					HttpStatus.NOT_FOUND);
		}
		con1.setContactId(contid);
		src.setContacts(con1);

		StatusCodes sta1 = statuscoderepository.findByStatusCode(statcode);
		if (sta1 == null) {
			logger.error("StatusCode with code {} not found.", statcode);
			return new ResponseEntity<>(new ErrorType("StatusCode with code " + statcode + " not found"),
					HttpStatus.NOT_FOUND);
		}
		sta1.setStatusCode(statcode);
		src.setStatusCodes(sta1);

		Problems pro1 = problemrepository.findByProblemCode(probcode);
		if (pro1 == null) {
			logger.error("Problem with code {} not found.", probcode);
			return new ResponseEntity<>(new ErrorType("Problem with code " + probcode + " not found"),
					HttpStatus.NOT_FOUND);
		}
		pro1.setProblemCode(probcode);
		src.setProblems(pro1);

		srcRepository.save(src);
		return new ResponseEntity<ServiceRequestCalls>(src, HttpStatus.OK);
	}
}
