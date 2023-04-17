package com.sojerdev.forwarder.carriage.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DriverRestController {

    private DriverService driverService;

    @Autowired
    public DriverRestController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/drivers")
    public List<Driver> findAll() {
        return driverService.findAll();
    }

    @GetMapping("/drivers/{driverId}")
    public Driver findById(@PathVariable int driverId) {
        return driverService.findById(driverId);
    }

    @PostMapping("/drivers")
    public void add(@RequestBody Driver driver) {
        // in case to force save method instead of update
        driver.setId(0);
        driverService.save(driver);
    }

    @PutMapping("/drivers")
    public void update(@RequestBody Driver driver) {
        driverService.save(driver);
    }

    @DeleteMapping("/drivers/{driverId}")
    public void delete(@PathVariable int id) {
        driverService.delete(id);
    }
}
