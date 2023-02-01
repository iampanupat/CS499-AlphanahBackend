package com.alphanah.alphanahbackend.repository;

import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.entity.ProductOption;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductOptionRepository extends CrudRepository<ProductOption, String> {

    List<ProductOption> findAllByRootProduct(Product product);

}
