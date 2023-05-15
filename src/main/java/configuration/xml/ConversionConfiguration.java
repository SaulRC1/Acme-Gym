package configuration.xml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConversionConfiguration {

    @Bean
    public StringToLocalDateConverter stringToLocalDateConverter() {
	return new StringToLocalDateConverter();
    }

    @Bean
    public StringToLocalTimeConverter stringToLocalTimeConverter() {
	return new StringToLocalTimeConverter();
    }

}
