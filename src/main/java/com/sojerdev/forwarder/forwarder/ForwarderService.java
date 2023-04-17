package com.sojerdev.forwarder.forwarder;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ForwarderService {

    List<Forwarder> findAll();

    Forwarder findById(int id);

    void save(Forwarder forwarder);

    void deleteById(int id);

    ResponseEntity<Object> getTotalValue(int id);
}
