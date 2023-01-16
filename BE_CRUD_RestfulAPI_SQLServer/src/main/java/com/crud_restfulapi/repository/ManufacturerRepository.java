package com.crud_restfulapi.repository;

import com.crud_restfulapi.model.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {

//    @Query("SELECT f FROM Food f WHERE f.name LIKE %:keyWord%")
//    public Page<Food> searchFoodByKeyWord(@Param("keyWord") String keyWord, Pageable pageable);

    Manufacturer findManufacturerByManufacturerName(String Name);
}
