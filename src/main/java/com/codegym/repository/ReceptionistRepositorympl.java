package com.codegym.repository;

import com.codegym.model.Receptionist;

import java.util.HashMap;
import java.util.Map;

public class ReceptionistRepositorympl implements ReceptionistRepository {
    private static Map<Integer, Receptionist> receptionists = new HashMap<>();

    static {
        receptionists.put(1, new Receptionist(1, "trâm anh", 18, "hà nội", "Bóng đá", "tramanh.jpg"));
        receptionists.put(2, new Receptionist(2, "ngọc trinh", 20, "thanh hóa", "Mua sắm", "ngoctrinh.jpg"));
        receptionists.put(3, new Receptionist(3, "vũ phương", 21, "hà nội", "Du lịch", "vuphuong.jpg"));
        receptionists.put(4, new Receptionist(4, "Maria Ozawa", 25, "Tokyo", "Xem phim", "mariaozawa.jpg"));
    }


    @Override
    public Map findAll() {
        return receptionists;
    }

    @Override
    public void save(Integer id, Receptionist receptionist) {
        receptionists.put(id, receptionist);
    }

    @Override
    public Receptionist findById(int id) {
        return receptionists.get(id);
    }

    @Override
    public void uplate(int id, Receptionist receptionist) {
        receptionists.put(id, receptionist);
    }

    @Override
    public void delete(int id) {
        receptionists.remove(id);
    }

    @Override
    public Map<Integer, Receptionist> searchByName(String name) {
        Map<Integer, Receptionist> filteredList = new HashMap<>();
        for (Map.Entry<Integer,Receptionist> receptionistEntry: receptionists.entrySet()) {
            if (receptionistEntry.getValue().getName().toLowerCase().contains(name.trim().toLowerCase())) {
                filteredList.put(receptionistEntry.getKey(),receptionistEntry.getValue());
            }
        }
        return filteredList;
    }
}
