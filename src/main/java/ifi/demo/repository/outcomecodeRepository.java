package ifi.demo.repository;

import org.springframework.data.repository.CrudRepository;

import ifi.demo.model.OutcomeCodes;

public interface outcomecodeRepository extends CrudRepository<OutcomeCodes, String>{

	OutcomeCodes findByOutcomeCode(String code);



}
