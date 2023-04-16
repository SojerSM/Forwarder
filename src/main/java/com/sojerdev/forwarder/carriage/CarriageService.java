package com.sojerdev.forwarder.carriage;

import java.util.List;

public interface CarriageService {

    List<Carriage> findAll();

    Carriage findById(int id);

    void save(Carriage carriage);

    void deleteById(int id);
}
