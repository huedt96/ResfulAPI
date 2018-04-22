package ifi.demo.repository;

import org.springframework.data.repository.CrudRepository;

import ifi.demo.model.ChargeCodes;

public interface chargecodeRepository extends CrudRepository<ChargeCodes, String>{

	ChargeCodes findByChargeCode(String code);

}
