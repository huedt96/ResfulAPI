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
@RequestMapping(path="/ServiceCalls/statuscode")
public class statuscodeController {

	public static final Logger logger = LoggerFactory.getLogger(statuscodeController.class);

	@Autowired
	private statuscodeRepository statuscoderepository;
	
	@PostMapping(path="/add")
	public @ResponseBody String addStatuscode (@RequestParam String code, @RequestParam String des, @RequestParam String eg) {
		StatusCodes sta = new StatusCodes();
		sta.setStatusCode(code);
		sta.setStatusDescription(des);
		sta.setEgCompleteorwip(eg);
		
		statuscoderepository.save(sta);
		return "Add Successfully";
	}
	@GetMapping(path="/get/all")
	public @ResponseBody Iterable<StatusCodes> getAllStatuscode() {
		return statuscoderepository.findAll();
	}
	
	@GetMapping(path ="/get/{code}")
	public @ResponseBody ResponseEntity<?> getByStatusCode (@PathVariable String code){
		StatusCodes statuscode = statuscoderepository.findByStatusCode(code);
		if(statuscode == null) {
			 logger.error("Action with code {} not found.", code);
			 return new ResponseEntity<>(new ErrorType("Action with code " + code + " not found"), HttpStatus.NOT_FOUND);
		 }
		return new ResponseEntity<>(statuscode, HttpStatus.OK);
	}
	
	@DeleteMapping(path="/delete/{code}")
	public @ResponseBody ResponseEntity<?> deleteStatusCode (@PathVariable String code) {
		StatusCodes statuscode = statuscoderepository.findByStatusCode(code);
		if(statuscode == null) {
			 logger.error("Action with code {} not found.", code);
			 return new ResponseEntity<>(new ErrorType("Action with code " + code + " not found"), HttpStatus.NOT_FOUND);
		 }
		statuscoderepository.delete(code);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(path ="/update/{code}")
	public @ResponseBody ResponseEntity<?> updateStatuscode (@PathVariable String code, @RequestParam String des, @RequestParam String eg) {
		StatusCodes currentSta = statuscoderepository.findByStatusCode(code);
		if(currentSta == null) {
			 logger.error("Action with code {} not found.", code);
			 return new ResponseEntity<>(new ErrorType("Action with code " + code + " not found"), HttpStatus.NOT_FOUND);
		 }
		currentSta.setStatusDescription(des);
		currentSta.setEgCompleteorwip(eg);
		
		statuscoderepository.save(currentSta);
		return new ResponseEntity<>(currentSta, HttpStatus.OK);
		
	}
}
