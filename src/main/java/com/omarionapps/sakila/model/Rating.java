package com.omarionapps.sakila.model;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Rating {
	G("G"), PG("PG"), PG13("PG-13"), R("R"), NC17("NC-17");

	private String rating;

	private Rating(String rating) {
		this.rating = rating;
	}

	// a map object to hold Rating Enum as value with its field as key.
	private static final Map<String, Rating> ratingMap;
	// Initializing the map object
	static {
		ratingMap = Stream.of(Rating.values()).collect(Collectors.toMap(elm -> elm.rating, elm -> elm));
	}

	/**
	 * Get the field of Enum
	 * @return the field of Enum
	 */
	public String getRating() {
		return this.rating;
	}
	
	/**
	 * Get the Rating Enum by searching with Enum field
	 * @param value the field value to search with.
	 * @return the Rating Enum
	 */
	public static Rating findByKey(String value) {
		return ratingMap.get(value);
	}

}
