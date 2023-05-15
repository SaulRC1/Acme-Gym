package configuration.xml;

import java.beans.PropertyEditorSupport;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class LocalTimeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
	try {
	    this.setValue(LocalTime.parse(text));
	} catch (DateTimeParseException e) {
	    throw new IllegalArgumentException("Invalid LocalTime format", e);
	}
    }

    @Override
    public String getAsText() {
	LocalTime localTime = (LocalTime) this.getValue();
	return localTime != null ? localTime.toString() : "";
    }

}
