package ifi.demo.repository;

import org.springframework.data.repository.CrudRepository;

import ifi.demo.model.ServiceRequestActions;

public interface serviceRaRepository extends CrudRepository<ServiceRequestActions, Integer>{

	ServiceRequestActions findByResponseId(Integer id);
	

	



}
