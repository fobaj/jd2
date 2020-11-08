package com.noirix.service;

import com.noirix.domain.Cars;
import com.noirix.domain.User;

import java.util.List;

public interface CarsService {
    List<Cars> findAll();

    Cars save(Cars car);

    Cars findById(Long carId);

    List<Cars> search(String query);

}
