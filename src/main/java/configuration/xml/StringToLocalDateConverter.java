
package configuration.xml;

import java.time.LocalDate;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * This class converts a string to a {@link java.time.LocalDate} object.<br>
 * <br>
 *
 * The main purpose of this converter is to be used inside PopulateDatabase.xml
 * in order to convert a string to a LocalDateObject
 *
 */

@Component
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(final String source) {
		return LocalDate.parse(source);
	}

}
