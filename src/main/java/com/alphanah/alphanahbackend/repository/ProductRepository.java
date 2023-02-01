package com.alphanah.alphanahbackend.repository;

import com.alphanah.alphanahbackend.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, String> {

}
