package ifi.demo.repository;

import org.springframework.data.repository.CrudRepository;

import ifi.demo.model.ServiceRequestCalls;

public interface serviceRcRepository extends CrudRepository<ServiceRequestCalls, Integer> {

	ServiceRequestCalls findByCallId(Integer id);

}
