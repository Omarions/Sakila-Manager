package com.omarionapps.sakila.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omarionapps.sakila.model.Language;
import com.omarionapps.sakila.repository.LanguageRepository;

@Service
public class LanguageService {
	@Autowired
	private LanguageRepository languageRepository;
	
	public List<Language> findAllByOrderByLanguageId(){
		return languageRepository.findAllByOrderByLanguageId();
	}

}
