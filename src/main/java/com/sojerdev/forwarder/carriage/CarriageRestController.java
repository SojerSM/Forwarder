package com.sojerdev.forwarder.carriage;

import com.sojerdev.forwarder.carriage.driver.Driver;
import com.sojerdev.forwarder.carriage.driver.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarriageRestController {

    private CarriageService carriageService;
    private DriverService driverService;

    @Autowired
    public CarriageRestController(CarriageService carriageService, DriverService driverService) {
        this.carriageService = carriageService;
        this.driverService = driverService;
    }

    @GetMapping("/carriages")
    public List<Carriage> findAll() {
        return carriageService.findAll();
    }

    @GetMapping("/carriages/{carriageId}")
    public Carriage findById(@PathVariable int carriageId) {
        Carriage carriage = carriageService.findById(carriageId);

        if (carriage == null) {
            throw new RuntimeException("Carriage with id " + carriageId + " was not found.");
        }
        return carriage;
    }

    @GetMapping("/carriages/{carriageId}/driver")
    public Driver find(@PathVariable int carriageId) {
        return driverService.findByCarriageId(carriageId);
    }

    @PostMapping("/carriages")
    public void addCarriage(@RequestBody Carriage carriage) {
        carriage.setId(0);
        carriageService.save(carriage);
    }

    @PutMapping("/carriages")
    public void updateCarriage(@RequestBody Carriage carriage) {
        Carriage prevCarriage = carriageService.findById(carriage.getId());

        // update everything except current carriage driver
        carriageService.save(carriage);
    }

    @DeleteMapping("/carriages/{carriageId}")
    public void deleteCarriage(@PathVariable int carriageId) {
        carriageService.deleteById(carriageId);
    }
}
