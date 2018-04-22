package ifi.demo.repository;

import org.springframework.data.repository.CrudRepository;

import ifi.demo.model.Problems;

public interface problemRepository extends CrudRepository<Problems, String>{

	Problems findByProblemCode(String code);

}
