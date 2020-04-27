import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperMethods {
	// set date time format
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		
	/**
	 * checks if string is in valid date format
	 * 
	 * @param possibleDate
	 * @return
	 */
	public boolean isDate(String possibleDate) {
		try {
			LocalDate.parse(possibleDate, formatter);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * checks if string is a double
	 * 
	 * @param possibleDouble
	 * @return
	 */
	public boolean isDouble(String possibleDouble) {
		try {
			Double.parseDouble(possibleDouble);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * checks if string is an integer
	 * 
	 * @param possibleInteger
	 * @return
	 */
	public boolean isInteger(String possibleInteger) {
		try {
			Integer.parseInt(possibleInteger);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
