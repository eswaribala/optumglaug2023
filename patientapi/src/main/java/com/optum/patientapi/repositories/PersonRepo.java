package com.optum.patientapi.repositories;

import com.optum.patientapi.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonRepo extends JpaRepository<Person, String>, JpaSpecificationExecutor<Person> {
}
