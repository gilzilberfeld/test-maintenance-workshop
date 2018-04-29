package workshop.testmaintenance.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.transaction.annotation.Isolation;

import workshop.testmaintenance.*;
import workshop.testmaintenance.categories.WrongPlace;
import workshop.testmaintenance.categories.DifferentType;
import workshop.testmaintenance.categories.Logic;
import workshop.testmaintenance.categories.Unreadable;


public class UnitTests {

	
	@Test
	@Category(Unreadable.class)
	public void CancelTheFirstNumber() {
	}
	
	@Test
	@Category(Unreadable.class)
	public void CancelTheFirstNumber2() {
	}
	
	@Test
	@Category(WrongPlace.class)
	public void REST_Calculation(){
		
	}
	
	@Test
	@Category(Isolation.class) 
	public void SomethingWithMemory(){
		// Maybe with spring injected @bean
	}
	
	@Test
	@Category({Logic.class, DifferentType.class})
	public void DependingOnConfiguration() {
		// read based on flag from configuration
		// Coming out of error?
		// Reset?
		// then if/else do something
	}
	
	
}
