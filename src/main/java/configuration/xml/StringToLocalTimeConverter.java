
package configuration.xml;

import java.time.LocalTime;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class converts a string to an instance of
 * {@link java.time.LocalTime}.<br>
 * <br>
 *
 * The main purpose of this converter is to be used inside PopulateDatabase.xml
 * to be able to convert from a string to a LocalTime object.
 */
@Component
public class StringToLocalTimeConverter implements Converter<String, LocalTime> {

	@Override
	public LocalTime convert(final String source) {
		return LocalTime.parse(source);
	}

}
