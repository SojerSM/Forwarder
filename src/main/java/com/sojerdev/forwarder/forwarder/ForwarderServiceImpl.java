package com.sojerdev.forwarder.forwarder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForwarderServiceImpl implements ForwarderService {

    private final ForwarderRepository forwarderRepository;

    @Autowired
    public ForwarderServiceImpl(ForwarderRepository forwarderRepository) {
        this.forwarderRepository = forwarderRepository;
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
            throw new RuntimeException("Forwarder with id - " + id + " was not found.");
        }

        return forwarder;
    }

    @Override
    public void save(Forwarder forwarder) {
        forwarderRepository.save(forwarder);
    }

    @Override
    public void deleteById(int id) {
        forwarderRepository.deleteById(id);
    }
}
