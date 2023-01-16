package com.crud_restfulapi.service;

import com.crud_restfulapi.model.Manufacturer;
import com.crud_restfulapi.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManufacturerServiceImpl implements IManufacturerService{

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public Manufacturer insertManufacturer(Manufacturer manufacturer) {
        return null;
    }

    @Override
    public Manufacturer updateManufacturer(long id, Manufacturer manufacturer) {
        return null;
    }

    @Override
    public void deleteManufacturer(long id) {

    }

    @Override
    public ArrayList getAllManufacturer() {
        ArrayList arrayManu = new ArrayList();
        List<Manufacturer> manufacturerList= manufacturerRepository.findAll();
        for (int i=0; i < manufacturerList.size() ; i++){
            arrayManu.add(manufacturerList.get(i).getManufacturerName());
        }
        return arrayManu;
    }

    @Override
    public List<Manufacturer> findByManufacturerName(String manufacturerName) {
        return null;
    }

    @Override
    public Manufacturer getOneManufacturer(long id) {
        return null;
    }
}
