package com.crud_restfulapi.controller;

import com.crud_restfulapi.model.Manufacturer;
import com.crud_restfulapi.service.IManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/manufacturers")
public class ManufacturerController {
    @Autowired
    private IManufacturerService manufacturerService;

    @GetMapping
    ResponseEntity<ArrayList> getAllManu(){
        ArrayList arrayManu= new ArrayList();
        arrayManu = manufacturerService.getAllManufacturer();

        return new ResponseEntity<ArrayList>( arrayManu, HttpStatus.OK);
    }
}
