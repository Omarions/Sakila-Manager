package com.omarionapps.sakila.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.omarionapps.sakila.model.Film;
import com.omarionapps.sakila.model.Rating;
import com.omarionapps.sakila.model.SpecialFeatures;
import com.omarionapps.sakila.service.CategoryService;
import com.omarionapps.sakila.service.FilmService;
import com.omarionapps.sakila.service.LanguageService;

@Controller
public class HomeController {
	private FilmService filmService;
	private LanguageService languageService;
	private CategoryService CategoryService;
	
	@Autowired
	public HomeController(FilmService filmService, LanguageService languageService, CategoryService categoryService) {
		this.filmService = filmService;
		this.languageService = languageService;
		this.CategoryService = categoryService;
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("auth/login");
	}
	
	@GetMapping("/home")
    public ModelAndView home() {
		ModelAndView model = new ModelAndView("index");
		
		int filmsCount = filmService.findAllByOrderById().size();
		List<Film> films = filmService.findAllByOrderById();
		
		model.addObject("filmsCount", filmsCount);
		model.addObject("films", films);
		model.addObject("categories", CategoryService.findAllByOrderByCategoryId());
        return model;
    }
	
	@GetMapping("/sakila/films/film/{filmId}")
	public ModelAndView getFilmForm(@PathVariable(name="filmId") int filmId){
		ModelAndView model = new ModelAndView("filmform");
		Optional<Film> optFilm = filmService.findById(filmId);
		if(optFilm.isPresent()) {
			model.addObject("film", optFilm.get());
			model.addObject("languages", languageService.findAllByOrderByLanguageId());
			model.addObject("ratings", Rating.values());
			model.addObject("specialFeatures", SpecialFeatures.values());
			model.addObject("categories", CategoryService.findAllByOrderByCategoryId());
		}else {
			model.addObject("messageError", "Film with ID(" + filmId + ") not found...");
		}
		return model;
	}
	
	@GetMapping("/sakila/films/film")
	public ModelAndView getFilmForm(){
		ModelAndView model = new ModelAndView("filmform");
		
		model.addObject("film", new Film());
		model.addObject("languages", languageService.findAllByOrderByLanguageId());
		model.addObject("ratings", Rating.values());
		model.addObject("specialFeatures", SpecialFeatures.values());
		model.addObject("categories", CategoryService.findAllByOrderByCategoryId());
		return model;
	}
	
	@GetMapping("/sakila/films/film/{filmId}/delete")
	public String deleteFilm(@PathVariable(name="filmId") int filmId, RedirectAttributes redirectAttrs) {
		Optional<Film> optFilm = filmService.findById(filmId);
		if(optFilm.isPresent()) {
			filmService.delete(optFilm.get());
			redirectAttrs.addFlashAttribute("messageSuccess", "Film with ID(" + filmId + ") has been deleted successfully!");
			return "redirect:/home";
		}else {
			redirectAttrs.addFlashAttribute("messageSuccess", "Error occurred while deleting the film with ID(" + filmId + ")...");
			return "redirect:/home";
		}
	}
	
	@PostMapping("/sakila/films/film")
	public String saveFilm(@Valid Film film, RedirectAttributes redirectAttrs, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return "redirect:/sakila/films/film";
		}else {
			Film savedFilm = filmService.save(film);
			redirectAttrs.addFlashAttribute("messageSuccess", "Film with ID(" + savedFilm.getFilmId() + ") has been saved successfully!");
			
			return "redirect:/home";
		}
		
	}
}
