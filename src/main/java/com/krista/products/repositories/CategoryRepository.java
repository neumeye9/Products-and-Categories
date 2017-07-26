package com.krista.products.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.krista.products.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
