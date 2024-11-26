package com.two_weeks_backend.two_weeks_backend.repositories.product;

import org.springframework.stereotype.Repository;

import com.two_weeks_backend.two_weeks_backend.entities.product.Product;
import com.two_weeks_backend.two_weeks_backend.repositories.BaseRepository;

@Repository
public interface ProductRepository extends BaseRepository<Product>{
}
