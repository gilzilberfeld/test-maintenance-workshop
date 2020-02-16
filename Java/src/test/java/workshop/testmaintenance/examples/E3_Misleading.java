package workshop.testmaintenance.examples;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import workshop.testmaintenance.Calculator;
import workshop.testmaintenance.User;
import workshop.testmaintenance.categories.Unreadable;

public class E3_Misleading {
	
	@Test
	// Data transformation
	public void userUpdatedCorrectly() {
		User user = new User();
		user.setName("Gil");
		assertEquals("Gil", user.getName());
		
		User anotherUser = new User();
		anotherUser.setName("Not Gil");
		user.updateFromAnother(anotherUser);
		assertEquals("Not Gil", user.getName());
	}
	
	@Test
	// Unclear name - is that the right expectation?
	public void cancelTheFirstNumber() {
		Calculator c = new Calculator();
		c.press("1");
		c.press("C");
		String result = c.getDisplay();
		assertEquals("0", result);
	}
	
	@Test
	// Code matching
	public void pressAndDisplay() {
		Calculator c = new Calculator();
		c.press("1");
		String result = c.getDisplay();
		assertEquals(Integer.toString(1), result);
	}
	
	@Test
	// No assertions
	public void cancelAfterOperation() {
		Calculator c = new Calculator();
		c.press("1");
		c.press("+");
		c.press("C");
		String result = c.getDisplay();
	}

}
