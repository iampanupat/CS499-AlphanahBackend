package com.alphanah.alphanahbackend.repository;

import com.alphanah.alphanahbackend.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, String> {

}
