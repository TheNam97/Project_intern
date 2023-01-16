package com.crud_restfulapi.model.DTO;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String productName;
    private String type;
    private String manufacturerName;
    private String description;
    private int year;
    private double price;
}
