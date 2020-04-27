import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
//Austin Rathje

/**
 *  JUnit tests for the helper methods
 */
class HelperMethodTests {
	// create HelperMethods object
	HelperMethods helpMethod = new HelperMethods();
	
	// check if isDate method is running properly
	@Test
	void testIsDate() {
		assertTrue(helpMethod.isDate("04-26-2020"));
		assertFalse(helpMethod.isDate("4-26-2020"));
		assertFalse(helpMethod.isDate("fthfecgr"));
	}

	// check if isDouble method is running properly
	@Test
	void testIsDouble() {
		assertTrue(helpMethod.isDouble("42.42"));
		assertFalse(helpMethod.isDouble("segee"));
		assertFalse(helpMethod.isDouble("34.5gd"));
	}

	// check if isInteger method is running properly
	@Test
	void testIsInteger() {
		assertTrue(helpMethod.isInteger("4"));
		assertFalse(helpMethod.isInteger("4.6"));
		assertFalse(helpMethod.isInteger("three"));
	}
}
