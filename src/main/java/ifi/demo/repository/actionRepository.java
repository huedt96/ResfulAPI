package ifi.demo.repository;

import org.springframework.data.repository.CrudRepository;

import ifi.demo.model.Actions;

public interface actionRepository extends CrudRepository<Actions, String>{

	Actions findByActionCode(String code);

}
