package com.codegym.service;

import com.codegym.model.Receptionist;

import java.util.Map;

public interface ReceptionistService {

    Map<Integer, Receptionist> findAll();

    void save(Integer id, Receptionist receptionist);

    Receptionist findById(int id);

    void uplate(int id, Receptionist receptionist);

    void delete(int id);

    Map<Integer, Receptionist> searchByName(String name);
}
