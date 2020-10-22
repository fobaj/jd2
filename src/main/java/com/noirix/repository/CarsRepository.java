package com.noirix.repository;

import com.noirix.domain.Cars;

import java.util.List;

public interface CarsRepository extends CrudRepository <Long, Cars> {

    List<Cars> search(String query);
}
