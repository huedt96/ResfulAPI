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
import ifi.demo.repository.problemRepository;

@Controller
@RequestMapping(path="/ServiceCalls/problem")
public class problemController {

	public static final Logger logger = LoggerFactory.getLogger(problemController.class);
	@Autowired
	private problemRepository problemrepository;
	
	@PostMapping(path="/add")
	public @ResponseBody String addProblem (@RequestParam String code, @RequestParam String des, @RequestParam String eg) {
		Problems pro = new Problems();
		pro.setProblemCode(code);
		pro.setProblemDescription(des);
		pro.setEgNoResponse(eg);
		
		problemrepository.save(pro);
		return "Add Successfully";
	}
	@GetMapping(path="/get/all")
	public @ResponseBody Iterable<Problems> getAllProblems() {
		return problemrepository.findAll();
	}
	
	@GetMapping(path ="/get/{code}")
	public @ResponseBody ResponseEntity<?> getByProblemCode (@PathVariable String code){
		Problems problem =  problemrepository.findByProblemCode(code);
		 if(problem == null) {
			 logger.error("Problem with code {} not found.", code);
			 return new ResponseEntity<>(new ErrorType("Problem with code " + code + " not found"), HttpStatus.NOT_FOUND);
		 }
		 return new ResponseEntity<Problems>(problem, HttpStatus.OK);
	}
	
	@DeleteMapping(path="/delete/{code}")
	public @ResponseBody ResponseEntity<?> deleteProblem (@PathVariable String code) {
		Problems problem =  problemrepository.findByProblemCode(code);
		 if(problem == null) {
			 logger.error("Problem with code {} not found.", code);
			 return new ResponseEntity<>(new ErrorType("Problem with code " + code + " not found"), HttpStatus.NOT_FOUND);
		 }
		 problemrepository.delete(code);
		 return new ResponseEntity<Problems>(HttpStatus.NO_CONTENT);
		
	}
	
	@PutMapping(path ="/update/{code}")
	public @ResponseBody ResponseEntity<?> updateProblem (@PathVariable String code, @RequestParam String des, @RequestParam String eg) {
		Problems currentPro = problemrepository.findByProblemCode(code);
		if(currentPro == null) {
			 logger.error("Problem with code {} not found.", code);
			 return new ResponseEntity<>(new ErrorType("Problem with code " + code + " not found"), HttpStatus.NOT_FOUND);
		 }
		currentPro.setProblemDescription(des);
		currentPro.setEgNoResponse(eg);
		
		problemrepository.save(currentPro);
		return new ResponseEntity<>(currentPro, HttpStatus.OK);
		
	}
	
	
}
