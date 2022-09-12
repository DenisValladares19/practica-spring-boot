package com.testcloud.testbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.testcloud.testbackend.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
