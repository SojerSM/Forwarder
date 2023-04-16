package com.sojerdev.forwarder.carriage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarriageRestController {

    private CarriageService carriageService;

    @Autowired
    public CarriageRestController(CarriageService carriageService) {
        this.carriageService = carriageService;
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

    @PostMapping("/carriages")
    public void addCarriage(@RequestBody Carriage carriage) {
        carriage.setCarriageId(0);
        carriageService.save(carriage);
    }

    @PutMapping("/carriages")
    public void updateCarriage(@RequestBody Carriage carriage) {
        Carriage prevCarriage = carriageService.findById(carriage.getCarriageId());

        // update everything except current carriage driver
        carriageService.save(carriage);
    }

    @DeleteMapping("/carriages/{carriageId}")
    public void deleteCarriage(@PathVariable int carriageId) {
        carriageService.deleteById(carriageId);
    }
}
