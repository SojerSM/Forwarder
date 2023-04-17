package com.sojerdev.forwarder.carriage;

import com.sojerdev.forwarder.carriage.driver.Driver;
import com.sojerdev.forwarder.carriage.driver.DriverService;
import com.sojerdev.forwarder.carriage.freight.Freight;
import com.sojerdev.forwarder.carriage.freight.FreightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarriageServiceImpl implements CarriageService{

    private CarriageRepository carriageRepository;
    private FreightService freightService;
    private DriverService driverService;

    @Autowired
    public CarriageServiceImpl(CarriageRepository carriageRepository, FreightService freightService, DriverService driverService) {
        this.carriageRepository = carriageRepository;
        this.freightService = freightService;
        this.driverService = driverService;
    }

    @Override
    public List<Carriage> findAll() {
        return carriageRepository.findAll();
    }

    @Override
    public Carriage findById(int id) {
        Optional<Carriage> result = carriageRepository.findById(id);

        Carriage carriage = null;

        if (result.isPresent()) {
            carriage = result.get();
        } else {
            throw new RuntimeException("Carriage with id - " + id + " was not found.");
        }
        return carriage;
    }

    @Override
    public void save(Carriage carriage) {
        carriageRepository.save(carriage);
    }

    @Override
    public void deleteById(int id) {
        carriageRepository.deleteById(id);
    }

    @Override
    public List<Freight> findBelongingFreights(int id) {
        List<Freight> freights = new ArrayList<>();

        for (Freight freight: freightService.findAll()) {
            if (freight.getCarriage().getId() == id) {
                freights.add(freight);
            }
        }
        return freights;
    }

    @Override
    public Driver findByCarriageId(int carriageId) {
        List<Driver> drivers = driverService.findAll();
        Driver driver = null;

        for (Driver d: drivers) {
            if (d.getCarriage().getId() == carriageId) {
                driver = d;
            }
        }

        findById(carriageId);

        if (driver == null) {
            throw new RuntimeException("There is no driver assigned to carriage with id " + carriageId);
        }
        return driver;
    }
}
