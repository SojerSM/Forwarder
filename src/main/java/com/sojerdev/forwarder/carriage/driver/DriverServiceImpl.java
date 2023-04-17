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
    public void save(Driver driver) {
        driverRepository.save(driver);
    }

    @Override
    public void deleteById(int id) {
        driverRepository.deleteById(id);
    }
}
