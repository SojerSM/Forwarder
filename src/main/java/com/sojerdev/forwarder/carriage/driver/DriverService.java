package com.sojerdev.forwarder.carriage.driver;

import java.util.List;

public interface DriverService {

    List<Driver> findAll();

    Driver findById(int id);

    Driver findByCarriageId(int carriageId);

    void save(Driver driver);

    void delete(int id);
}