package com.testcloud.testbackend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.testcloud.testbackend.dto.PersonDTO;
import com.testcloud.testbackend.entity.Person;
import com.testcloud.testbackend.repository.PersonRepository;
import com.testcloud.testbackend.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PersonRepository personRepository;

	@Override
	public PersonDTO save(PersonDTO dto) {
		
		if(dto == null) {
			new Exception("El PersonDTO es requerido");
		}
		
		Person person = modelMapper.map(dto, Person.class);
		personRepository.save(person);
		return modelMapper.map(person, PersonDTO.class);
	}

	@Override
	public PersonDTO byId(Long personId) {
		Person person = personRepository.findById(personId).orElse(null);

		if (person == null) {
			new Exception("No se encontro la persona");
		}

		return modelMapper.map(person, PersonDTO.class);
	}

	@Override
	public List<PersonDTO> getAll() {
		List<Person> list = personRepository.findAll();

		if (list == null) {
			return new ArrayList<PersonDTO>();
		}

		return list.stream().map(element -> modelMapper.map(element, PersonDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public PersonDTO update(Long personId, PersonDTO dto) {
		if (personId == null) {
			new Exception("El person id es requerido");
		}
		
		Person personFound = personRepository.findById(personId).orElse(null);
		if (personFound == null) {
			new Exception("Persona no encontrada");
		}

		personFound.setAge(dto.getAge());
		personFound.setEmail(dto.getEmail());
		personFound.setName(dto.getName());
		personFound.setLastName(dto.getLastName());
		personRepository.save(personFound);

		return modelMapper.map(personFound, PersonDTO.class);
	}

}
