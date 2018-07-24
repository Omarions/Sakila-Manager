package com.omarionapps.sakila.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "film_id", columnDefinition = "TINYINT")
	private int filmId;
	@NotEmpty(message = "Required Field")
	@NotNull
	private String title;
	private String description;
	@Column(name = "release_year")
	private int releaseYear;
	@ManyToOne
	@JoinColumn(name = "language_id", referencedColumnName = "language_id")
	@NotNull(message = "Required Field")
	private Language language;
	@ManyToOne
	@JoinColumn(name = "original_language_id", referencedColumnName = "language_id")
	private Language originalLang;
	@Column(name = "rental_duration", columnDefinition = "TINYINT")
	@NotNull(message = "Required Field")
	private int rentalDuration;
	@Column(name = "rental_rate")
	@NotNull(message = "Required Field")
	private BigDecimal rentalRate;
	private int length;
	@Column(name = "replacement_cost")
	@NotNull(message = "Required Field")
	private BigDecimal replacementCost;
	@Convert(converter = RatingConverter.class)
	private Rating rating;
	@Convert(converter = SpecialFeaturesConverter.class)
	private EnumSet<SpecialFeatures> specialFeatures;
	@Column(name = "last_update")
	@NotNull(message = "Required Field")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/M/yyyy hh:mm:ss a")
	private LocalDateTime lastUpdate;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "film_category", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();

	public Film() {

	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Language getOriginalLang() {
		return originalLang;
	}

	public void setOriginalLang(Language originalLang) {
		this.originalLang = originalLang;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public BigDecimal getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public BigDecimal getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(BigDecimal replacementCost) {
		this.replacementCost = replacementCost;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public EnumSet<SpecialFeatures> getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(EnumSet<SpecialFeatures> specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + ", description=" + description + ", releaseYear="
				+ releaseYear + ", language=" + language.getName() + ", originalLang=" + originalLang.getName()
				+ ", rentalDuration=" + rentalDuration + ", rentalRate=" + rentalRate + ", length=" + length
				+ ", replacementCost=" + replacementCost + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + filmId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (filmId != other.filmId)
			return false;
		return true;
	}

}
