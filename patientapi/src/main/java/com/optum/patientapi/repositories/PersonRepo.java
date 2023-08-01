package com.optum.patientapi.repositories;

import com.optum.patientapi.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, String> {
}
