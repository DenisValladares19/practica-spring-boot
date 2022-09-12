package com.testcloud.testbackend.controller;

import com.testcloud.testbackend.dto.PersonDTO;
import com.testcloud.testbackend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAll() {
        List<PersonDTO> list = personService.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> save(@RequestBody PersonDTO dto) {
        PersonDTO person = personService.save(dto);
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<PersonDTO> update(
            @RequestBody PersonDTO dto,
            @PathVariable("personId") Long personId
    ) {
        PersonDTO person = personService.update(personId, dto);
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDTO> getById(@PathVariable("personId") Long personId) {
        PersonDTO person = personService.byId(personId);
        return new ResponseEntity(person, HttpStatus.OK);
    }
}
