package com.test.backend.dos.service;

import com.test.backend.dos.dto.CarDTO;

import java.util.List;

public interface CarService {
    CarDTO save(CarDTO dto);
    CarDTO update(Long carId, CarDTO dto);
    List<CarDTO> getAll();
    CarDTO byId(Long carId);
}
