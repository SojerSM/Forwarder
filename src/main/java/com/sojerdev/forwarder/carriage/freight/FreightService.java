package com.sojerdev.forwarder.carriage.freight;

import java.util.List;

public interface FreightService {

    List<Freight> findAll();

    Freight findById(int id);

    void save(Freight freight);

    void delete(int id);
}
