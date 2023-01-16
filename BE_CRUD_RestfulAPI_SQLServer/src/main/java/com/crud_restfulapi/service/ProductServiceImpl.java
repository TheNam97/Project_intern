package com.crud_restfulapi.service;

import com.crud_restfulapi.Exception.APIException;
import com.crud_restfulapi.Exception.ResourceNotFoundException;
import com.crud_restfulapi.model.DTO.Convert;
import com.crud_restfulapi.model.DTO.ProductDTO;
import com.crud_restfulapi.model.Manufacturer;
import com.crud_restfulapi.model.Product;
import com.crud_restfulapi.repository.ManufacturerRepository;
import com.crud_restfulapi.repository.ProductRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ManufacturerRepository manufacturerRepository;
    @Autowired
    Convert convert;


    @Override
    public ProductDTO insertProduct(ProductDTO productDTO) {

        List<Product> productsList = productRepository.findByProductName(productDTO.getProductName());

        if (productsList.size()>0){ throw new APIException(HttpStatus.BAD_REQUEST, "Username is already taken"); }
        else {
            Manufacturer manufacturer = manufacturerRepository.findManufacturerByManufacturerName(productDTO.getManufacturerName());

            productRepository.save(convert.ProducDTO_ToEntity(productDTO , manufacturer));
        }
        return productDTO;
    }

    @Override
    public ProductDTO updateProduct(long id, ProductDTO productDTO) {

        Manufacturer manufacturerP = manufacturerRepository.findManufacturerByManufacturerName(productDTO.getManufacturerName());

        productRepository.findById(id).map(product1 -> {
           product1.setProductName(productDTO.getProductName());
            product1.setType(productDTO.getType());
            product1.setManufacturerP(manufacturerP);
           product1.setDescription(productDTO.getDescription());
           product1.setYear(productDTO.getYear());
            product1.setPrice(productDTO.getPrice());
            productRepository.save(product1);
            return convert.ProducEntity_ToDTO(product1,manufacturerP.getManufacturerName());
        });

        return null;
    }

    @Override
    public void deleteProduct(long id) {
        Product product = productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product","id",id));
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        List<ProductDTO> productDTOList = convert.toListProductDTO(productRepository.findAll());
        return productDTOList;
    }

    @Override
    public List<ProductDTO> findByProductName(String productName) {
        List<ProductDTO> productDTOList = convert.toListProductDTO(productRepository.findLikeName(productName));

        if(productDTOList.size()<1){
            // throw new APIException(HttpStatus.NOT_FOUND,"ko tim thay" );
            throw new ResourceNotFoundException("Product","Name",productName);
        }
        return productDTOList;
    }
    @Override
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "D:\\Java\\Intern";
        //File file = ResourceUtils.getFile("classpath:jasper/webproduct.jrxml");
        List<ProductDTO> productDTOList = convert.toListProductDTO(productRepository.findAll());
        //List<Product> productList = productRepository.findAll();

        JasperReport jasperReport = JasperCompileManager.compileReport("D:\\Java\\Spring\\CRUD_RestfulAPI_SQLServer\\src\\main\\resources\\jasper\\webproduct.jrxml");//biên dịch báo cáo, và trả về đối tượng của lớp báo cáo
        String downloadUri = "Ok!";
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productDTOList);
        Map<String, Object> infos = new HashMap<String, Object>();
        infos.put("presenter", "Nguyen The Nam");
        infos.put("title", "Web Product");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, infos, dataSource);// điền data vào báo cáo rồi print
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\products.pdf");

//        //JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\products.pdf"); // xuất báo cáo theo định dạng
//        if (reportFormat.equalsIgnoreCase("html")) {
//            downloadUri = "http://localhost:8081/products/download/product.html";
//            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\products.html");
//        }
//        if (reportFormat.equalsIgnoreCase("pdf")) {
//            downloadUri = "http://localhost:8081/products/download/products.pdf";
//             // xuất báo cáo theo định dạng
//        }
        return downloadUri;
    }
}
