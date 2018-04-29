package workshop.testmaintenance.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import workshop.testmaintenance.categories.DifferentType;
import workshop.testmaintenance.categories.WrongPlace;

// Should be selenium tests
public class UITests {

	@Test
	@Category(DifferentType.class)
	public void DivisionByZero() {
		//fail("Not yet implemented");
	}
	
	@Test
	@Category(WrongPlace.class)
	public void REST_() {
		//“0”,”5″ => “5”
	}
	

}
