package com.alphanah.alphanahbackend.repository;

import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.entity.ProductOption;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProductOptionRepository extends CrudRepository<ProductOption, UUID> {

    List<ProductOption> findAllByProduct(Product product);

}
