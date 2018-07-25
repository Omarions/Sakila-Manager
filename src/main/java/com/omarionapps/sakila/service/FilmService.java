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

	/**
	 * Get all films ordered by its ID
	 * @return List<Film> in the table ordered by ID
	 */
	public List<Film> findAllByOrderById(){
		List<Film> list = filmRepository.findAllByOrderByFilmId();
		System.out.println("List: " + list);
		return list;
	}
	
	/**
	 * Get film by its ID
	 * @param id to search with
	 * @return optional object
	 */
	public Optional<Film> findById(int id) {
		return filmRepository.findById(id);
	}
	
	/**
	 * Save the film whether new or update old one.
	 * @param film to be saved
	 * @return the saved film
	 */
	public Film save(Film film) {
		return filmRepository.save(film);
	}
	
	/**
	 * Delete the film
	 * @param film to be deleted.
	 */
	public void delete(Film film) {
		filmRepository.delete(film);
	}
}
