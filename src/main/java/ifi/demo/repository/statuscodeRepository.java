package ifi.demo.repository;

import org.springframework.data.repository.CrudRepository;

import ifi.demo.model.StatusCodes;

public interface statuscodeRepository extends CrudRepository<StatusCodes, String>{

	StatusCodes findByStatusCode(String code);

}
