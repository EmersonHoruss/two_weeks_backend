package com.two_weeks_backend.two_weeks_backend.repositories.catalog;

import org.springframework.stereotype.Repository;

import com.two_weeks_backend.two_weeks_backend.entities.catalog.Product;
import com.two_weeks_backend.two_weeks_backend.repositories.BaseRepository;

@Repository
public interface ProductRepository extends BaseRepository<Product> {

}
