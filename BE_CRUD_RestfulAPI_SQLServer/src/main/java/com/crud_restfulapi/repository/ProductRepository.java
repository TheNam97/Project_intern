package com.crud_restfulapi.repository;

import com.crud_restfulapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByProductName(String productName);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:productName%")
    List<Product> findLikeName( String productName);
}
