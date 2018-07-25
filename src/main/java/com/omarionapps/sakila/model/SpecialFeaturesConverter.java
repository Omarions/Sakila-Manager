package com.omarionapps.sakila.model;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * An Attribute Converter for SpecailFeatures Enum
 * 
 * @author Omar
 *
 */
@Converter
public class SpecialFeaturesConverter implements AttributeConverter<EnumSet<SpecialFeatures>, String> {

	@Override
	public String convertToDatabaseColumn(EnumSet<SpecialFeatures> features) {
		//create a string from special feature values joined with ','.
		String result = features.stream().map(feature -> feature.getSpecialFeature()).collect(Collectors.joining(","));

		return result;
	}

	@Override
	public EnumSet<SpecialFeatures> convertToEntityAttribute(String value) {
		EnumSet<SpecialFeatures> eFeatures = null;
		Set<String> features = Stream.of(value.split(",")).collect(Collectors.toSet());
		String[] arrFeatures = features.toArray(new String[features.size()]);

		//check if there is only one value 
		if (arrFeatures.length == 1) {
			eFeatures = EnumSet.of(SpecialFeatures.findByKey(arrFeatures[0].trim()));
		} // Or it has more values
		else if (arrFeatures.length > 1) {
			//Get the Enum by its field
			SpecialFeatures from = SpecialFeatures.findByKey(arrFeatures[0].trim());
			SpecialFeatures to = SpecialFeatures.findByKey(arrFeatures[arrFeatures.length - 1].trim());

			//create EnumSet with SpecialFeatures Enums
			eFeatures = EnumSet.of(from, to);
		}

		return eFeatures;
	}

}
