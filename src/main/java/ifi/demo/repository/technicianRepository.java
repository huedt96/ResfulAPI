package ifi.demo.repository;

import org.springframework.data.repository.CrudRepository;

import ifi.demo.model.Technicians;

public interface technicianRepository extends CrudRepository<Technicians, Integer>{

	Technicians findByTechnicianId(Integer id);


}
