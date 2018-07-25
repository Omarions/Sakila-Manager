package com.omarionapps.sakila.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
/**
 * An attribute converter for Rating Enum
 * 
 * @author Omarion
 *
 */
@Converter
public class RatingConverter implements AttributeConverter<Rating, String> {

	/**
	 * Return the string from Rating Enum
	 */
	@Override
	public String convertToDatabaseColumn(Rating rating) {
		return rating.getRating();
	}

	/**
	 * Return Rating Enum from string value.
	 */
	@Override
	public Rating convertToEntityAttribute(String value) {
		return Rating.findByKey(value);
		
	}

}
