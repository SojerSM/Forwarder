package com.sojerdev.forwarder.carriage;

import com.sojerdev.forwarder.carriage.freight.Freight;
import com.sojerdev.forwarder.carriage.freight.FreightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarriageServiceImpl implements CarriageService{

    private CarriageRepository carriageRepository;
    private FreightRepository freightRepository;

    @Autowired
    public CarriageServiceImpl(CarriageRepository carriageRepository, FreightRepository freightRepository) {
        this.carriageRepository = carriageRepository;
        this.freightRepository = freightRepository;
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

        for (Freight freight: freightRepository.findAll()) {
            if (freight.getCarriage().getId() == id) {
                freights.add(freight);
            }
        }
        return freights;
    }
}
