package com.omarionapps.sakila.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omarionapps.sakila.model.Category;
import com.omarionapps.sakila.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAllByOrderByCategoryId(){
		return categoryRepository.findAllByOrderByCategoryId();
	}

}
