/**
 * Mapping Film Table
 */
package com.omarionapps.sakila.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Omar
 *
 */
@Entity
public class Film implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "film_id", columnDefinition = "TINYINT")
	private int filmId;
	@NotEmpty(message = "Required Field")
	@NotNull
	private String title;
	@Column(columnDefinition="TEXT")
	private String description;
	@Column(name = "release_year")
	@Min(value=1000)
	@Max(value=9999)
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
	@DecimalMin(value="0.00")
	@DecimalMax(value="99.99")
	private BigDecimal rentalRate;
	@Column(name="length")
	private int length;
	@Column(name = "replacement_cost")
	@NotNull(message = "Required Field")
	@DecimalMin(value="0.00")
	@DecimalMax(value="999.99")
	private BigDecimal replacementCost;
	@Convert(converter = RatingConverter.class)
	private Rating rating;
	@Convert(converter = SpecialFeaturesConverter.class)
	private EnumSet<SpecialFeatures> specialFeatures;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update")
	private Date lastUpdate;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "film_category", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();

	public Film() {

	}

	/**
	 * @return
	 */
	public int getFilmId() {
		return filmId;
	}

	/**
	 * @param filmId
	 */
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return
	 */
	public int getReleaseYear() {
		return releaseYear;
	}

	/**
	 * @param releaseYear
	 */
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	/**
	 * @return
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * @param language
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}

	/**
	 * @return
	 */
	public Language getOriginalLang() {
		return originalLang;
	}

	/**
	 * @param originalLang
	 */
	public void setOriginalLang(Language originalLang) {
		this.originalLang = originalLang;
	}

	/**
	 * @return
	 */
	public int getRentalDuration() {
		return rentalDuration;
	}

	/**
	 * @param rentalDuration
	 */
	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	/**
	 * @return
	 */
	public BigDecimal getRentalRate() {
		return rentalRate;
	}

	/**
	 * @param rentalRate
	 */
	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}

	/**
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return
	 */
	public BigDecimal getReplacementCost() {
		return replacementCost;
	}

	/**
	 * @param replacementCost
	 */
	public void setReplacementCost(BigDecimal replacementCost) {
		this.replacementCost = replacementCost;
	}

	/**
	 * @return
	 */
	public Rating getRating() {
		return rating;
	}

	/**
	 * @param rating
	 */
	public void setRating(Rating rating) {
		this.rating = rating;
	}

	/**
	 * @return
	 */
	public EnumSet<SpecialFeatures> getSpecialFeatures() {
		return specialFeatures;
	}

	/**
	 * @param specialFeatures
	 */
	public void setSpecialFeatures(EnumSet<SpecialFeatures> specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	/**
	 * @return
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * @return
	 */
	public Set<Category> getCategories() {
		return categories;
	}

	/**
	 * @param categories
	 */
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + ", description=" + description + ", releaseYear="
				+ releaseYear + ", rentalDuration=" + rentalDuration + ", rentalRate=" + rentalRate + ", length=" + length
				+ ", replacementCost=" + replacementCost + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + filmId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
