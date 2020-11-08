package com.noirix.service.impl;

import com.noirix.domain.Cars;
import com.noirix.repository.CarsRepository;
import com.noirix.service.CarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class CarsServiceImpl implements CarsService {

    //private final CarsRepository carRepository;

    private CarsRepository carRepository;

    public CarsServiceImpl(CarsRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Cars> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Cars save(Cars car) {
        //1. Validation layer
        //2. Convert http request params into User object
        //3. Extended calls into DB or external services
        return carRepository.save(car);
    }

    @Override
    public Cars findById(Long carId) {
        return carRepository.findById(carId);
    }

    @Override
    public List<Cars> search(String query) {
        return carRepository.search(query); //Ctrl+Alb+B - go to implementation of method
    }

}
