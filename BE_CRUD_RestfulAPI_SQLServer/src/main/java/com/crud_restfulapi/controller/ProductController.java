package com.crud_restfulapi.controller;

import com.crud_restfulapi.model.DTO.ProductDTO;
import com.crud_restfulapi.model.Product;
import com.crud_restfulapi.model.responseObject;
import com.crud_restfulapi.service.IProductService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path="/api/products")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @GetMapping
    ResponseEntity<List<ProductDTO>> getAllProduct(){
        return new ResponseEntity<List<ProductDTO>>( iProductService.getAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/{name}")  //Địa chỉ nhận request
    ResponseEntity<List<ProductDTO>> findByName(@PathVariable String name){
        List<ProductDTO> listProduct = iProductService.findByProductName(name);
        return new ResponseEntity<List<ProductDTO>>( listProduct, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/insert")
    ResponseEntity<ProductDTO> insertProduct(@RequestBody ProductDTO productDTO){
        ProductDTO productAdd = iProductService.insertProduct(productDTO);
        return new ResponseEntity<ProductDTO>(productAdd, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("{id}")
    ResponseEntity<ProductDTO> updateProduct(@PathVariable long id,@RequestBody ProductDTO productDTO ){
        ProductDTO foundProduct = iProductService.updateProduct(id,productDTO);
        return  new ResponseEntity<ProductDTO>(foundProduct, HttpStatus.OK);
    }
    @GetMapping(value = "/report/{format}")
    public ResponseEntity<String> exportReport(@PathVariable("format") String format) throws JRException, IOException {
        String downloadUri = iProductService.exportReport(format);
        return new ResponseEntity<>(downloadUri, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id){
        iProductService.deleteProduct(id);
    }
}
