package com.testcloud.testbackend.service;

import java.util.List;

import com.testcloud.testbackend.dto.PersonDTO;

public interface PersonService {
	PersonDTO save(PersonDTO dto);
	PersonDTO update(Long personId, PersonDTO dto);
	PersonDTO byId(Long personId);
	List<PersonDTO> getAll();
}
