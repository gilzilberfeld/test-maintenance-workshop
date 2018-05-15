package workshop.testmaintenance.tests;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.transaction.annotation.Isolation;
import workshop.testmaintenance.*;
import workshop.testmaintenance.categories.WrongPlace;
import workshop.testmaintenance.categories.Assertions;
import workshop.testmaintenance.categories.DifferentType;
import workshop.testmaintenance.categories.Information;
import workshop.testmaintenance.categories.Logic;
import workshop.testmaintenance.categories.Unreadable;


public class UnitTests {


	@Test
	@Category(Unreadable.class)
	public void CancelTheFirstNumber() {
		Calculator c = new Calculator();
		c.press("1");
		c.press("C");
		String result = c.getDisplay();
		assertEquals("0", result);
	}

	@Test
	@Category(Unreadable.class)
	public void CancelTheFirstNumber2() {
		Calculator c = new Calculator();
		c.press("1");
		c.press("C");
		c.press("2");
		String result = c.getDisplay();
		assertEquals("2", result);
	}

	@Test
	@Category(Isolation.class)
	public void displayOnlyIfUserIsLoggedIn(){
		Calculator calc = new Calculator();
		if (calc.getCurrentUser() != null)
			assertEquals("0",calc.getDisplay());
	}
	
	@Value
	private String memory;
	
	@Test
	@Category({Logic.class, DifferentType.class})
	public void restoreMemoryAndContinue() {
		Calculator calculator = new Calculator();
		String lastStoredValue = calculator.getLastValueFor("Gil");
		if (lastStoredValue == memory) {
			calculator.press("2");
			String result = calculator.getDisplay();
			assertEquals("12", result);
		}
	}

	@Test
	@Category(Assertions.class)
	public void CancelAfterOperation() {
		Calculator c = new Calculator();
		c.press("1");
		c.press("+");
		c.press("C");
		String result = c.getDisplay();
	}

	@Test
	@Category (Information.class)
 	public void NotEnoughInfo() {
		Calculator c = new Calculator();
		c.press("1");
		String result = c.getDisplay();
		assertTrue(result.equals("1"));
	}

	@Test
	@Category (Information.class)
	public void RedundnatTest1() {
		Calculator c = new Calculator();
		c.press("1");
		c.press("2");
		String result = c.getDisplay();
		assertEquals(result, "12");
	}

	@Test
	@Category (Information.class)
	public void RedundnatTest2() {
		Calculator c = new Calculator();
		c.press("9");
		c.press("5");
		String result = c.getDisplay();
		assertEquals(result, "95");
	}

	@Ignore
	@Test
	@Category (Information.class)
	public void RedundnatTest4() {
		Calculator c = new Calculator();
		c.press("7");
		c.press("3");
		String result = c.getDisplay();
		assertEquals(result, "73");
	}
	
	@Test
	@Category (WrongPlace.class)
	// long running test in the unit test section
	public void orderOfOperations() {
		/// This is a long test
		Calculator c = new Calculator();
		c.press("1");
		c.press("+");
		c.press("2");
		c.press("*");
		c.press("4");
		c.press("=");
		String result = c.getDisplay();
		assertEquals(result, "9");
	}

}
