package com.test.backend.dos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private Long carId;
    private String brand;
    private String model;
    private Integer year;
}
