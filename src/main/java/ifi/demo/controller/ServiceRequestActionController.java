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
import ifi.demo.repository.*;

@Controller
@RequestMapping(path = "/ServiceCalls/sractions")
public class ServiceRequestActionController {

	public static final Logger logger = LoggerFactory.getLogger(ServiceRequestCallController.class);

	@Autowired
	private serviceRaRepository srarepository;
	@Autowired
	private actionRepository actionrepository;
	@Autowired
	private outcomecodeRepository outrepository;
	@Autowired
	private technicianRepository technicianrepository;
	@Autowired
	private serviceRcRepository srcRepository;

	@PostMapping(path = "/add")
	public @ResponseBody ResponseEntity<?> addSRActions(
			@RequestParam String actioncode, 
			@RequestParam String outcode,
			@RequestParam int techid, 
			@RequestParam int callid, 
			@RequestParam String datetime) {
		ServiceRequestActions sra = new ServiceRequestActions();
		sra.setResponseDateTime(datetime);

		Actions act1 = actionrepository.findByActionCode(actioncode);
		if (act1 == null) {
			logger.error("Action with code {} not found.", actioncode);
			return new ResponseEntity<>(new ErrorType("Action with code " + actioncode + " not found"),
					HttpStatus.NOT_FOUND);
		}
		act1.setActionCode(actioncode);
		sra.setActions(act1);

		OutcomeCodes out1 = outrepository.findByOutcomeCode(outcode);
		if (out1 == null) {
			logger.error("OutcomeCode with code {} not found.", outcode);
			return new ResponseEntity<>(new ErrorType("OutcomeCode with " + outcode + " not found"),
					HttpStatus.NOT_FOUND);
		}
		out1.setOutcomeCode(outcode);
		sra.setOutcomeCodes(out1);

		Technicians tech1 = technicianrepository.findByTechnicianId(techid);
		if (tech1 == null) {
			logger.error("Technician with id {} not found.", techid);
			return new ResponseEntity<>(new ErrorType("Technician with id " + techid + " not found"),
					HttpStatus.NOT_FOUND);
		}
		tech1.setTechnicianId(techid);
		sra.setTechnicians(tech1);

		ServiceRequestCalls call1 = srcRepository.findByCallId(callid);
		if (call1 == null) {
			logger.error("ServiceRequestCall with id {} not found.", callid);
			return new ResponseEntity<>(new ErrorType("ServiceRequestCall with id " + callid + " not found"),
					HttpStatus.NOT_FOUND);
		}
		call1.setCallId(callid);
		sra.setServiceRequestCalls(call1);

		srarepository.save(sra);
		return new ResponseEntity<ServiceRequestActions>(sra, HttpStatus.OK);
	}

	@GetMapping(path = "/get/{id}")
	public @ResponseBody ResponseEntity<?> getId(@PathVariable int id) {
		ServiceRequestActions sra = srarepository.findByResponseId(id);
		if (sra == null) {
			logger.error("ServiceRequestAction with id {} not found.", id);
			return new ResponseEntity<>(new ErrorType("ServiceRequestAction with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ServiceRequestActions>(sra, HttpStatus.OK);
	}

	@GetMapping(path = "/get/all")
	public @ResponseBody Iterable<ServiceRequestActions> getAll() {
		return srarepository.findAll();
	}

	@DeleteMapping(path = "/delete/{id}")
	public @ResponseBody ResponseEntity<?> deleteServiceRequestAction(@PathVariable int id) {
		ServiceRequestActions sra = srarepository.findByResponseId(id);
		if (sra == null) {
			logger.error("ServiceRequestAction with id {} not found.", id);
			return new ResponseEntity<>(new ErrorType("ServiceRequestAction with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		srarepository.delete(id);
		return new ResponseEntity<ServiceRequestActions>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(path = "/update/{id}")
	public @ResponseBody ResponseEntity<?> updateSRAction(@PathVariable int id, @RequestParam String actioncode,
			@RequestParam String outcode, @RequestParam int techid, @RequestParam int callid,
			@RequestParam String datetime) {
		ServiceRequestActions sra = srarepository.findByResponseId(id);
		if (sra == null) {
			logger.error("ServiceRequestAction with id {} not found.", id);
			return new ResponseEntity<Object>(new ErrorType("ServiceRequestAction with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		sra.setResponseDateTime(datetime);

		Actions act1 = actionrepository.findByActionCode(actioncode);
		if (act1 == null) {
			logger.error("Action with code {} not found.", actioncode);
			return new ResponseEntity<>(new ErrorType("Action with code " + actioncode + " not found"),
					HttpStatus.NOT_FOUND);
		}
		act1.setActionCode(actioncode);
		sra.setActions(act1);

		OutcomeCodes out1 = outrepository.findByOutcomeCode(outcode);
		if (out1 == null) {
			logger.error("OutcomeCode with code {} not found.", outcode);
			return new ResponseEntity<>(new ErrorType("OutcomeCode with " + outcode + " not found"),
					HttpStatus.NOT_FOUND);
		}
		out1.setOutcomeCode(outcode);
		sra.setOutcomeCodes(out1);

		Technicians tech1 = technicianrepository.findByTechnicianId(techid);
		if (tech1 == null) {
			logger.error("Technician with id {} not found.", techid);
			return new ResponseEntity<>(new ErrorType("Technician with id " + techid + " not found"),
					HttpStatus.NOT_FOUND);
		}
		tech1.setTechnicianId(techid);
		sra.setTechnicians(tech1);

		ServiceRequestCalls call1 = srcRepository.findByCallId(callid);
		if (call1 == null) {
			logger.error("ServiceRequestCall with id {} not found.", callid);
			return new ResponseEntity<>(new ErrorType("ServiceRequestCall with id " + callid + " not found"),
					HttpStatus.NOT_FOUND);
		}
		call1.setCallId(callid);
		sra.setServiceRequestCalls(call1);

		srarepository.save(sra);
		return new ResponseEntity<ServiceRequestActions>(sra, HttpStatus.OK);
	}
}
