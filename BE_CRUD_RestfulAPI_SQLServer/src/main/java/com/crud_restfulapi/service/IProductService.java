package com.crud_restfulapi.service;

import com.crud_restfulapi.model.DTO.ProductDTO;
import com.crud_restfulapi.model.Product;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;


public interface IProductService {
    public ProductDTO insertProduct(ProductDTO product);
    public ProductDTO updateProduct(long id, ProductDTO product);
    public void deleteProduct(long id);
    public List<ProductDTO> getAllProduct();
    public List<ProductDTO> findByProductName(String productName);
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException;
    //public ProductDTO getOneProduct(long id);
}
