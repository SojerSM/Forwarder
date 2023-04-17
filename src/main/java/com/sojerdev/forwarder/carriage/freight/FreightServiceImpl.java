package com.sojerdev.forwarder.carriage.freight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FreightServiceImpl implements FreightService {

    private FreightRepository freightRepository;

    @Autowired
    public FreightServiceImpl(FreightRepository freightRepository) {
        this.freightRepository = freightRepository;
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
        freightRepository.save(freight);
    }

    @Override
    public void delete(int id) {
        freightRepository.deleteById(id);
    }
}
