package com.test.backend.dos.service.impl;

import com.test.backend.dos.dto.CarDTO;
import com.test.backend.dos.entity.Car;
import com.test.backend.dos.repository.CarRepository;
import com.test.backend.dos.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements  CarService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CarRepository carRepository;

    @Override
    public CarDTO save(CarDTO dto) {
        Car car = modelMapper.map(dto, Car.class);
        car = carRepository.save(car);
        return modelMapper.map(car, CarDTO.class);
    }

    @Override
    public CarDTO update(Long carId, CarDTO dto) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new NotFoundException("Car not found id: " + carId));

        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setYear(dto.getYear());
        carRepository.save(car);

        return modelMapper.map(car, CarDTO.class);
    }

    @Override
    public List<CarDTO> getAll() {
        List<Car> list = carRepository.findAll();

        if (list == null) {
            new Exception("List car is empty");
        }

        return list.stream().map(element -> {
            return modelMapper.map(element, CarDTO.class);
        }).collect(Collectors.toList());
    }

    @Override
    public CarDTO byId(Long carId) {
        Car car = carRepository.findById(carId).orElse(null);

        if (car == null) {
            new Exception("Car not found by id: " + carId);
        }

        return modelMapper.map(car, CarDTO.class);
    }
}
