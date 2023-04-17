package com.sojerdev.forwarder.carriage.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    private DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    @Override
    public Driver findById(int id) {
        Optional<Driver> result = driverRepository.findById(id);
        Driver driver = null;

        if (result.isPresent()) {
            driver = result.get();
        } else {
            throw new RuntimeException("Driver with id - " + id + " was not found.");
        }
        return driver;
    }

    @Override
    public Driver findByCarriageId(int carriageId) {
        List<Driver> drivers = findAll();
        Driver driver = null;

        for (Driver d: drivers) {
            if (d.getId() == carriageId) {
                driver = d;
            }
        }

        if (driver == null) {
            throw new RuntimeException("There is no driver assigned to carriage with id - " + carriageId);
        }

        return driver;
    }

    @Override
    public void save(Driver driver) {
        driverRepository.save(driver);
    }

    @Override
    public void delete(int id) {
        driverRepository.deleteById(id);
    }
}
