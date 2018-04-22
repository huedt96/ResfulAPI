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
@RequestMapping(path="/ServiceCalls/technician")
public class TechnicianController {

	public static final Logger logger = LoggerFactory.getLogger(TechnicianController.class);

	@Autowired
	private technicianRepository technicianrepository;
	
	
	@PostMapping(path ="/add")
	public @ResponseBody String addTechnician (@RequestParam String name,@RequestParam String eg) {
		Technicians tec = new Technicians();
		tec.setTechnicianName(name);
		tec.setEgBobbyCaldwell(eg);
		
		technicianrepository.save(tec);
		return "Add Successfully";
	}
	
	@GetMapping(path="/get/all")
	public @ResponseBody Iterable<Technicians> getAllTechnicians() {
		return technicianrepository.findAll();
	}

	@GetMapping(path ="/get/{id}")
	public @ResponseBody ResponseEntity<?> getByTechnicianId (@PathVariable int id){
		Technicians technician = technicianrepository.findByTechnicianId(id);
		 if(technician == null) {
			 logger.error("Technician with id {} not found.", id);
			 return new ResponseEntity<>(new ErrorType("Technician with id " + id + " not found"), HttpStatus.NOT_FOUND);
		 }
		 return new ResponseEntity<Technicians>(technician, HttpStatus.OK);
	}
	
	@DeleteMapping(path="/delete/{id}")
	public @ResponseBody ResponseEntity<?> deleteTechnician (@PathVariable int id) {
		Technicians technician = technicianrepository.findByTechnicianId(id);
		 if(technician == null) {
			 logger.error("Technician with id {} not found.", id);
			 return new ResponseEntity<>(new ErrorType("Technician with id " + id + " not found"), HttpStatus.NOT_FOUND);
		 }
		 technicianrepository.delete(id);
		 return new ResponseEntity<Technicians>(HttpStatus.NO_CONTENT);
	}
	@PutMapping(path ="/update/{id}")
	public @ResponseBody ResponseEntity<?> updateTechnician (@PathVariable int id, @RequestParam String name,@RequestParam String eg) {
		Technicians currentTec = technicianrepository.findByTechnicianId(id);
		if(currentTec == null) {
			 logger.error("Technician with id {} not found.", id);
			 return new ResponseEntity<>(new ErrorType("Technician with id " + id + " not found"), HttpStatus.NOT_FOUND);
		 }
		currentTec.setTechnicianName(name);
		currentTec.setEgBobbyCaldwell(eg);
		
		technicianrepository.save(currentTec);
		return new ResponseEntity<Technicians>(currentTec,HttpStatus.OK);
		
	}

}
