
package com.omarionapps.sakila.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.omarionapps.sakila.model.Film;

@Repository
public interface FilmRepository extends CrudRepository<Film, Integer> {
	List<Film> findAllByOrderByTitle();
	List<Film> findAllByOrderByFilmId();

}
