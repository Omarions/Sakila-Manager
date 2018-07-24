package com.omarionapps.sakila.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RatingConverter implements AttributeConverter<Rating, String> {

	@Override
	public String convertToDatabaseColumn(Rating rating) {
		System.out.println("Rating: " + rating.toString());
		return rating.getRating();
	}

	@Override
	public Rating convertToEntityAttribute(String value) {
		switch (value) {
		case "G":
			return Rating.G;
		case "PG":
			return Rating.PG;
		case "PG-13":
			return Rating.PG13;
		case "R":
			return Rating.R;
		case "NC-17":
			return Rating.NC17;
				
		}
		return null;
		
	}

}
