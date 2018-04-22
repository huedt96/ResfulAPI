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
import ifi.demo.repository.outcomecodeRepository;

@Controller
@RequestMapping(path="/ServiceCalls/outcomecode")
public class outcomecodeController {

	public static final Logger logger = LoggerFactory.getLogger(contactController.class);

	@Autowired
	private outcomecodeRepository outrepository;
	
	@PostMapping(path="/add")
	public @ResponseBody String addOutcomecode (
			@RequestParam String code, 
			@RequestParam String des, 
			@RequestParam String eg) {
		OutcomeCodes out = new OutcomeCodes();
		out.setOutcomeCode(code);
		out.setOutcomeDescription(des);
		out.setEgProblemFixed(eg);
		
		outrepository.save(out);
		return "Add Successfully";
	}
	@GetMapping(path="/get/all")
	public @ResponseBody Iterable<OutcomeCodes> getAllOutcomeCodes() {
		return outrepository.findAll();
	}
	
	@GetMapping(path ="/get/{code}")
	public @ResponseBody ResponseEntity<?> getByOutcomeCode (@PathVariable String code){
		OutcomeCodes out = outrepository.findByOutcomeCode(code);
		if(out == null) {
			logger.error("OutcomeCode with code {} not found.", code);
			return new ResponseEntity<>(new ErrorType("OutcomeCode with "+ code+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<OutcomeCodes>(out, HttpStatus.OK);
	}
	
	@DeleteMapping(path="/delete/{code}")
	public @ResponseBody ResponseEntity<?> deleteOutcomeCode (@PathVariable String code) {
		OutcomeCodes out = outrepository.findByOutcomeCode(code);
		if(out == null) {
			logger.error("OutcomeCode with code {} not found.", code);
			return new ResponseEntity<>(new ErrorType("OutcomeCode with "+ code+ " not found"), HttpStatus.NOT_FOUND);
		}
		outrepository.delete(code);
		return new ResponseEntity<OutcomeCodes>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(path ="/update/{code}")
	public @ResponseBody ResponseEntity<?> updateOutcomeCode (
			@PathVariable String code, 
			@RequestParam String des, 
			@RequestParam String eg) {
		OutcomeCodes currentOut = outrepository.findByOutcomeCode(code);
		if(currentOut == null) {
			logger.error("OutcomeCode with code {} not found.", code);
			return new ResponseEntity<>(new ErrorType("OutcomeCode with "+ code+ " not found"), HttpStatus.NOT_FOUND);
		}
		currentOut.setOutcomeDescription(des);
		currentOut.setEgProblemFixed(eg);
		
		outrepository.save(currentOut);
		return new ResponseEntity<OutcomeCodes>(currentOut, HttpStatus.OK);
		
	}
}
