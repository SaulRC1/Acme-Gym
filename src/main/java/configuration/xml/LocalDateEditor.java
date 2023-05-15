package configuration.xml;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class LocalDateEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
	try {
	    this.setValue(LocalDate.parse(text));
	} catch (DateTimeParseException e) {
	    throw new IllegalArgumentException("Invalid LocalTime format", e);
	}
    }

    @Override
    public String getAsText() {
	LocalDate localDate = (LocalDate) this.getValue();
	return localDate != null ? localDate.toString() : "";
    }
}
