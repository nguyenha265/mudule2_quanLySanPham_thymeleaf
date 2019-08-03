package com.codegym.repository;


import com.codegym.model.Receptionist;

import java.util.Map;

public interface ReceptionistRepository {

    Map<Integer, Receptionist> findAll();

    void save(Integer id, Receptionist receptionist);

    Receptionist findById(int id);

    void uplate(int id, Receptionist receptionist);

    void delete(int id);

    Map<Integer, Receptionist> searchByName(String name);
}
