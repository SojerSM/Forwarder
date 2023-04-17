package com.sojerdev.forwarder.carriage;

import com.sojerdev.forwarder.carriage.driver.Driver;
import com.sojerdev.forwarder.carriage.freight.Freight;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarriageService {

    List<Carriage> findAll();

    Carriage findById(int id);

    void save(Carriage carriage);

    void deleteById(int id);

    List<Freight> findBelongingFreights(int id);

    Driver findByCarriageId(int carriageId);

    ResponseEntity<Object> getValues(int id);

}
