package com.crud_restfulapi.model.DTO;

import com.crud_restfulapi.model.Manufacturer;
import com.crud_restfulapi.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Convert {
    public List<ProductDTO> toListProductDTO(List<Product> productList) {
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (int i=0; i< productList.size(); i++){

            ProductDTO productDTO = new ProductDTO();

            productDTO.setId(productList.get(i).getId());
            productDTO.setProductName(productList.get(i).getProductName());
            productDTO.setType(productList.get(i).getType());
            productDTO.setManufacturerName(productList.get(i).getManufacturerP().getManufacturerName());
            productDTO.setDescription(productList.get(i).getDescription());
            productDTO.setYear(productList.get(i).getYear());
            productDTO.setPrice(productList.get(i).getPrice());

            productDTOList.add(productDTO);
        }

        return productDTOList;
    }
    public Product ProducDTO_ToEntity(ProductDTO productDTO, Manufacturer manufacturer){
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setProductName(productDTO.getProductName());
        product.setType(productDTO.getType());
        product.setManufacturerP(manufacturer);
        product.setDescription(productDTO.getDescription());
        product.setYear(productDTO.getYear());
        product.setPrice(productDTO.getPrice());

        return product;
    }

    public ProductDTO ProducEntity_ToDTO(Product product, String manufacturerName){
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setProductName(product.getProductName());
        productDTO.setType(product.getType());
        productDTO.setManufacturerName(manufacturerName);
        productDTO.setDescription(product.getDescription());
        productDTO.setYear(product.getYear());
        productDTO.setPrice(product.getPrice());

        return productDTO;
    }

}
