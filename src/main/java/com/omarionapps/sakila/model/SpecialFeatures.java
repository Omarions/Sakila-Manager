package com.omarionapps.sakila.model;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represent values for special feature field in film table
 * 
 * @author Omarion
 *
 */
public enum SpecialFeatures {
	TRAILERS("Trailers"), COMMENTARIES("Commentaries"), DELETED_SCENES("Deleted Scenes"), BEHIND_SCENES("Behind the Scenes");
	
	private final String specialFeature;
	
	private SpecialFeatures(String specialFeature) {
		this.specialFeature = specialFeature;
	}
	
	//a map object to hold SpecialFeature Enum as value with its field as key.
	private static final Map<String, SpecialFeatures> featuresMap;
	//Initializing the map object
	static {
		featuresMap = Stream.of(SpecialFeatures.values()).collect(Collectors.toMap(elm -> elm.specialFeature, elm -> elm));
	}
	
	/**
	 * Get the field of Enum
	 * @return the field of Enum
	 */
	public String getSpecialFeature() {
		return specialFeature;
	}
	
	/**
	 * Get the SpecialFeaure Enum by searching with Enum field
	 * @param value the field value to search with.
	 * @return the SpecialFeature Enum
	 */
	public static SpecialFeatures findByKey(String value) {
		return featuresMap.get(value);
	}
	
}
