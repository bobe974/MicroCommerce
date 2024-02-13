package com.ecommerce.microcommerce.repository;

import com.ecommerce.microcommerce.modele.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {
    List<Product> findByPrixGreaterThan(Integer prix);
}