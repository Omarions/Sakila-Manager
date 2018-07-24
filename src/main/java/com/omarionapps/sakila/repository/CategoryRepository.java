package com.omarionapps.sakila.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.omarionapps.sakila.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

	List<Category> findAllByOrderByCategoryId();

}
