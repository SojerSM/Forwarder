package com.sojerdev.forwarder.forwarder;

import com.sojerdev.forwarder.carriage.freight.Freight;
import com.sojerdev.forwarder.carriage.freight.FreightService;
import com.sojerdev.forwarder.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ForwarderServiceImpl implements ForwarderService {

    private final ForwarderRepository forwarderRepository;
    private final FreightService freightService;

    @Autowired
    public ForwarderServiceImpl(ForwarderRepository forwarderRepository, FreightService freightService) {
        this.forwarderRepository = forwarderRepository;
        this.freightService = freightService;
    }

    @Override
    public List<Forwarder> findAll() {
        return forwarderRepository.findAll();
    }

    @Override
    public Forwarder findById(int id) {
        Optional<Forwarder> result = forwarderRepository.findById(id);

        Forwarder forwarder = null;

        if (result.isPresent()) {
            forwarder = result.get();
        } else {
            throw new NotFoundException("Forwarder with id - " + id + " was not found.");
        }

        return forwarder;
    }

    @Override
    public void save(Forwarder forwarder) {
        forwarderRepository.save(forwarder);
    }

    @Override
    public void deleteById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Forwarder with id - " + id + " was not found.");
        }
        forwarderRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Object> getTotalValue(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Forwarder with id - " + id + " was not found.");
        }
        List<Freight> freights = freightService.findAll();
        int totalValue = 0;
        int numOfFreights = 0;

        for (Freight freight: freights) {
            if (freight.getCarriage().getForwarder().getId() == id) {
                totalValue += freight.getValue();
                numOfFreights++;
            }
        }
        Map<String, Integer> response = new HashMap<>();
        response.put("freights", numOfFreights);
        response.put("value", totalValue);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
