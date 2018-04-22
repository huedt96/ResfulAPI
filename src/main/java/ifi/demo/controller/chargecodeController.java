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
import ifi.demo.model.ChargeCodes;
import ifi.demo.repository.chargecodeRepository;

	@Controller
	@RequestMapping(path="/ServiceCalls/chargecode")
	public class chargecodeController {

		public static final Logger logger = LoggerFactory.getLogger(chargecodeController.class);
		@Autowired
		private chargecodeRepository chargecoderepository;
		
		@PostMapping(path="/add")
		public @ResponseBody String addChargecode (@RequestParam String code, @RequestParam String des) {
			ChargeCodes chargecode = new ChargeCodes();
			chargecode.setChargeCode(code);
			chargecode.setChargeDescription(des);
			
			chargecoderepository.save(chargecode);
			return "Add Successfully";
		}
		@GetMapping(path="/get/all")
		public @ResponseBody Iterable<ChargeCodes> getAllChargecodes() {
			return chargecoderepository.findAll();
		}
		
		@GetMapping(path ="/get/{code}")
		public @ResponseBody ResponseEntity<?> getByChargeCode (@PathVariable String code){
			ChargeCodes chargecode = chargecoderepository.findByChargeCode(code);
			if(chargecode == null) {
				logger.error("ChargeCode with code {} not found.", code);
				 return new ResponseEntity<>(new ErrorType("ChargeCode with code " + code + " not found"), HttpStatus.NOT_FOUND);
			 }
			 return new ResponseEntity<ChargeCodes>(chargecode, HttpStatus.OK);
		}
		
		@DeleteMapping(path="/delete/{code}")
		public @ResponseBody ResponseEntity<?> deleteChargecode (@PathVariable String code) {
			ChargeCodes chargecode = chargecoderepository.findByChargeCode(code);
			if(chargecode == null) {
				logger.error("ChargeCode with code {} not found.", code);
				 return new ResponseEntity<>(new ErrorType("ChargeCode with code " + code + " not found"), HttpStatus.NOT_FOUND);
			 }
			chargecoderepository.delete(chargecode);
			 return new ResponseEntity<ChargeCodes>(HttpStatus.NO_CONTENT);
		}
		
		@PutMapping(path ="/update/{code}")
		public @ResponseBody ResponseEntity<?> updateChargecode (@PathVariable String code, @RequestParam String des) {
			ChargeCodes chargecode = chargecoderepository.findByChargeCode(code);
			if(chargecode == null) {
				logger.error("ChargeCode with code {} not found.", code);
				 return new ResponseEntity<>(new ErrorType("ChargeCode with code " + code + " not found"), HttpStatus.NOT_FOUND);
			 }
			chargecode.setChargeDescription(des);
			
			chargecoderepository.save(chargecode);
			return new ResponseEntity<ChargeCodes>(chargecode, HttpStatus.OK);
			
		}
		
		
	}

