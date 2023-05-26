package com.projet5.safetynet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projet5.safetynet.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>  {

}
