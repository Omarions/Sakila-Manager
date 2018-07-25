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
	
	/**
	 * Get Login view
	 * @return login page
	 */
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("auth/login");
	}
	
	/**
	 * Get home view and populate it with list of films and count of them.
	 * @return home page
	 */
	@GetMapping("/home")
    public ModelAndView home() {
		ModelAndView model = new ModelAndView("index");
		
		int filmsCount = filmService.findAllByOrderById().size();
		List<Film> films = filmService.findAllByOrderById();
		
		System.out.println("Films:- " + films);
		
		model.addObject("filmsCount", filmsCount);
		model.addObject("films", films);

        return model;
    }
	
	/**
	 * Get Film form for updating film
	 * @param filmId the film ID to be updated
	 * @return the film form page
	 */
	@GetMapping("/sakila/films/film/{filmId}")
	public ModelAndView getFilmForm(@PathVariable(name="filmId") int filmId){
		ModelAndView model = new ModelAndView("filmform");
		Optional<Film> optFilm = filmService.findById(filmId);
		//check if the film is existed
		if(optFilm.isPresent()) {
			prepareModel(model, optFilm.get());
		}else {
			model.addObject("messageError", "Film with ID(" + filmId + ") not found...");
		}
		return model;
	}
	
	/**
	 * Get film form page for adding new film and populate it with empty object of Film
	 * @return film form page
	 */
	@GetMapping("/sakila/films/film")
	public ModelAndView getFilmForm(){
		ModelAndView model = new ModelAndView("filmform");
		prepareModel(model, new Film());
		
		return model;
	}
	
	/**
	 * Delete a film
	 * @param filmId to get the film to be deleted
	 * @param redirectAttrs to send the messages of success or failure. 
	 * @return the home path
	 */
	@GetMapping("/sakila/films/film/{filmId}/delete")
	public String deleteFilm(@PathVariable(name="filmId") int filmId, RedirectAttributes redirectAttrs) {
		Optional<Film> optFilm = filmService.findById(filmId);
		//check if the film exists
		if(optFilm.isPresent()) {
			filmService.delete(optFilm.get());
			redirectAttrs.addFlashAttribute("messageSuccess", "Film with ID(" + filmId + ") has been deleted successfully!");
			return "redirect:/home";
		}else {
			redirectAttrs.addFlashAttribute("messageSuccess", "Error occurred while deleting the film with ID(" + filmId + ")...");
			return "redirect:/home";
		}
	}
	
	/**
	 * Save the film whether new or updated
	 * @param film the object retrieved from the form
	 * @param redirectAttrs to send the messages of success of failure
	 * @param bindingResult to validate the inputs and check for any errors in data
	 * @return whether the film form again if there is any errors or the home path if the operation is success
	 */
	@PostMapping("/sakila/films/film")
	public String saveFilm(@Valid Film film, RedirectAttributes redirectAttrs, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return "filmform";
		}else {
			Film savedFilm = filmService.save(film);
			redirectAttrs.addFlashAttribute("messageSuccess", "Film with ID(" + savedFilm.getFilmId() + ") has been saved successfully!");
			
			return "redirect:/home";
		}
		
	}
	
	/**
	 * Prepare the model with required data
	 * @param model to hold data to the view
	 * @param film the film object to be sent in the model.
	 */
	private void prepareModel(ModelAndView model, Film film) {
		model.addObject("film", film);
		model.addObject("languages", languageService.findAllByOrderByLanguageId());
		model.addObject("ratings", Rating.values());
		model.addObject("specialFeatures", SpecialFeatures.values());
		model.addObject("categories", CategoryService.findAllByOrderByCategoryId());
	}
}
