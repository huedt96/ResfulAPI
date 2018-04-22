package ifi.demo.repository;

import org.springframework.data.repository.CrudRepository;

import ifi.demo.model.Contacts;

public interface contactRepository extends CrudRepository<Contacts, Integer>{

	Contacts findByContactId(Integer id);
}
