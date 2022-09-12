package com.testcloud.testbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
	private Long PersonId;
	private String name;
	private String lastName;
	private Integer age;
	private String email;
}
