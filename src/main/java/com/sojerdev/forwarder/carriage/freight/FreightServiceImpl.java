package com.sojerdev.forwarder.carriage.freight;

import com.sojerdev.forwarder.carriage.Carriage;
import com.sojerdev.forwarder.carriage.CarriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FreightServiceImpl implements FreightService {

    private FreightRepository freightRepository;
    private CarriageService carriageService;

    @Autowired
    public FreightServiceImpl(FreightRepository freightRepository, @Lazy CarriageService carriageService) {
        this.freightRepository = freightRepository;
        this.carriageService = carriageService;
    }

    @Override
    public List<Freight> findAll() {
        return freightRepository.findAll();
    }

    @Override
    public Freight findById(int id) {
        Optional<Freight> result = freightRepository.findById(id);
        Freight freight = null;

        if (result.isPresent()) {
            freight = result.get();
        } else {
            throw new RuntimeException("Freight with id - " + id + " was not found.");
        }
        return freight;
    }

    @Override
    public void save(Freight freight) {
        Carriage carriage = carriageService.findById(freight.getCarriage().getId());

        if (carriage.getDriver() == null) {
            throw new RuntimeException("Given carriage doesn't have any driver assigned.");
        }
        freightRepository.save(freight);
    }

    @Override
    public void delete(int id) {
        freightRepository.deleteById(id);
    }
}
