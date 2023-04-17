package com.sojerdev.forwarder.forwarder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ForwarderRestController {

    private ForwarderService forwarderService;

    @Autowired
    public ForwarderRestController(ForwarderService forwarderService) {
        this.forwarderService = forwarderService;
    }

    @GetMapping("/forwarders")
    public List<Forwarder> findAll() {
        return forwarderService.findAll();
    }

    @GetMapping("/forwarders/{forwarderId}")
    public Forwarder findById(@PathVariable int forwarderId) {
        return forwarderService.findById(forwarderId);
    }

    @GetMapping("/forwarders/{forwarderId}/value")
    public ResponseEntity<Object> getTotalValue(@PathVariable int forwarderId) {
        return forwarderService.getTotalValue(forwarderId);
    }

    @PostMapping("/forwarders")
    public void addForwarder(@RequestBody Forwarder forwarder) {
        // in case to force save method instead of update
        forwarder.setId(0);
        forwarderService.save(forwarder);
    }

    @PutMapping("/forwarders")
    public void updateForwarder(@RequestBody Forwarder forwarder) {
        Forwarder prevForwarder = forwarderService.findById(forwarder.getId());

        // update everything except carriages list - change person data not linked vehicles
        forwarder.setCarriages(prevForwarder.getCarriages());
        forwarderService.save(forwarder);
    }

    @DeleteMapping("/forwarders/{forwarderId}")
    public void deleteForwarder(@PathVariable int forwarderId) {
        forwarderService.deleteById(forwarderId);
    }
}
