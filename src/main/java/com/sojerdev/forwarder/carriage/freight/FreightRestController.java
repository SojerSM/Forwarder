package com.sojerdev.forwarder.carriage.freight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FreightRestController {

    private FreightService freightService;

    @Autowired
    public FreightRestController(FreightService freightService) {
        this.freightService = freightService;
    }

    @GetMapping("/freights")
    public List<Freight> findAll() {
        return freightService.findAll();
    }

    @GetMapping("/freights/{freightId}")
    public Freight findById(@PathVariable int freightId) {
        Freight freight = freightService.findById(freightId);

        if (freight == null) {
            throw new RuntimeException("Freight with id - " + freightId + " was not found.");
        }
        return freight;
    }

    @PostMapping("/freights")
    public void addFreight(@RequestBody Freight freight) {
        freight.setId(0);
        freightService.save(freight);
    }

    @PutMapping("/freights")
    public void updateFreight(@RequestBody Freight freight) {
        freightService.save(freight);
    }

    @DeleteMapping("/freights/{freightId}")
    public void delete(@PathVariable int freightId) {
        freightService.delete(freightId);
    }
}
