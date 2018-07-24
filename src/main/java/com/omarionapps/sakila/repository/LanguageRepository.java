package com.omarionapps.sakila.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.omarionapps.sakila.model.Language;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Integer> {

	List<Language> findAllByOrderByLanguageId();

}
