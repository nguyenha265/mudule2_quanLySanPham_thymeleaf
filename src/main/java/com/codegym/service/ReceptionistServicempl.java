package com.codegym.service;

import com.codegym.model.Receptionist;
import com.codegym.repository.ReceptionistRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class ReceptionistServicempl implements ReceptionistService {

    @Autowired
    private ReceptionistRepository receptionistRepository;

    @Override
    public Map<Integer, Receptionist> findAll() {
        return receptionistRepository.findAll();
    }

    @Override
    public void save(Integer id, Receptionist receptionist) {
        receptionistRepository.save(id, receptionist);
    }

    @Override
    public Receptionist findById(int id) {
        return receptionistRepository.findById(id);
    }

    @Override
    public void uplate(int id, Receptionist receptionist) {
        receptionistRepository.uplate(id, receptionist);
    }

    @Override
    public void delete(int id) {
        receptionistRepository.delete(id);

    }

    @Override
    public Map<Integer, Receptionist> searchByName(String name) {
        return receptionistRepository.searchByName(name);
    }

}
