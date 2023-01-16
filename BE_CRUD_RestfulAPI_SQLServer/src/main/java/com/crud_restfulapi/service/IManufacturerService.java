package com.crud_restfulapi.service;

import com.crud_restfulapi.model.Manufacturer;
import com.crud_restfulapi.model.Product;

import java.util.ArrayList;
import java.util.List;

public interface IManufacturerService {
    public Manufacturer insertManufacturer(Manufacturer manufacturer);
    public Manufacturer updateManufacturer(long id, Manufacturer manufacturer);
    public void deleteManufacturer(long id);
    public ArrayList getAllManufacturer();
    public List<Manufacturer> findByManufacturerName(String manufacturerName);
    public Manufacturer getOneManufacturer(long id);
}
