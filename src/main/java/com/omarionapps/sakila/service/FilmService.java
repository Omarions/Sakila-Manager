package com.omarionapps.sakila.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omarionapps.sakila.model.Film;
import com.omarionapps.sakila.repository.FilmRepository;

@Service
public class FilmService {
	@Autowired
	private FilmRepository filmRepository;

	public List<Film> findAllByOrderById(){
		return filmRepository.findAllByOrderByFilmId();
	}
	
	public List<Film> findAllByOrderByTitle(){
		return filmRepository.findAllByOrderByTitle();
	}
	
	public Optional<Film> findById(int id) {
		return filmRepository.findById(id);
	}
	
	public Film save(Film film) {
		return filmRepository.save(film);
	}
	
	public void delete(Film film) {
		filmRepository.delete(film);
	}
}
