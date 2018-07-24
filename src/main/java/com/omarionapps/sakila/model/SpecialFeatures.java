package com.omarionapps.sakila.model;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SpecialFeatures {
	TRAILERS("Trailers"), COMMENTARIES("Commentaries"), DELETED_SCENES("Deleted Scenes"), BEHIND_SCENES("Behind the Scenes");
	
	private final String specialFeature;
	
	private SpecialFeatures(String specialFeature) {
		this.specialFeature = specialFeature;
	}
	
	private static final Map<String, SpecialFeatures> featuresMap;
	static {
		featuresMap = Stream.of(SpecialFeatures.values()).collect(Collectors.toMap(elm -> elm.specialFeature, elm -> elm));
	}
	
	public String getSpecialFeature() {
		return specialFeature;
	}
	
	public static SpecialFeatures findByKey(String value) {
		return featuresMap.get(value);
	}
	
}
