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
import ifi.demo.repository.actionRepository;
@Controller
@RequestMapping(path="/ServiceCalls/action")
public class actionController {

	public static final Logger logger = LoggerFactory.getLogger(actionController.class);
	@Autowired
	private actionRepository actionrepository;
	
	@PostMapping(path="/add")
	public @ResponseBody String addAction (
			@RequestParam String code, 
			@RequestParam String des, 
			@RequestParam String eg) {
		Actions act = new Actions();
		act.setActionCode(code);
		act.setActionDescription(des);
		act.setEgChecktheCircuits(eg);
		
		actionrepository.save(act);
		return "Add Successfully";
	}
	@GetMapping(path="/get/all")
	public @ResponseBody Iterable<Actions> getAllActions() {
		return actionrepository.findAll();
	}
	
	@GetMapping(path ="/get/{code}")
	public @ResponseBody ResponseEntity<?> getByActionCode (@PathVariable String code){
		Actions action =  actionrepository.findByActionCode(code);
		 if(action == null) {
			 logger.error("Action with code {} not found.", code);
			 return new ResponseEntity<>(new ErrorType("Action with code " + code + " not found"), HttpStatus.NOT_FOUND);
		 }
		 return new ResponseEntity<Actions>(action, HttpStatus.OK);
	}
	
	@DeleteMapping(path="/delete/{code}")
	public @ResponseBody ResponseEntity<?> deleteAction (@PathVariable String code) {
		Actions action =  actionrepository.findByActionCode(code);
		 if(action == null) {
			 logger.error("Action with code {} not found.", code);
			 return new ResponseEntity<>(new ErrorType("Action with code " + code + " not found"), HttpStatus.NOT_FOUND);
		 }
		 actionrepository.delete(code);
		 return new ResponseEntity<Actions>(HttpStatus.NO_CONTENT);
		
	}
	
	@PutMapping(path ="/update/{code}")
	public @ResponseBody ResponseEntity<?> updateAction (
			@PathVariable String code, 
			@RequestParam String des, 
			@RequestParam String eg) {
		Actions currentAct = actionrepository.findByActionCode(code);
		if(currentAct == null) {
			 logger.error("Action with code {} not found.", code);
			 return new ResponseEntity<>(new ErrorType("Action with code " + code + " not found"), HttpStatus.NOT_FOUND);
		 }
		currentAct.setActionDescription(des);
		currentAct.setEgChecktheCircuits(eg);
		
		actionrepository.save(currentAct);
		return new ResponseEntity<Actions>(currentAct, HttpStatus.OK);
		
	}
}
