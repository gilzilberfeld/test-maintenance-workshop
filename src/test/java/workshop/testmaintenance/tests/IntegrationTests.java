package workshop.testmaintenance.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import workshop.testmaintenance.categories.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTests{

	@Test
	@Category(WrongPlace.class)
	public void Overflow() {
		
	}
	
	@Test
	@Category(Isolation.class)
	public void DependentOnLoggedOnUser() {
		// “5”,”+”,"
	}

	
	@Test
	@Category ({Logic.class, Isolation.class})
	public void AlsoDependentOnUser() {
		// If user is logged on
		// “5”,”+”,"
	}
	
	
	///////////////////
	/// These two should be last
	
	@Test
	@Category(Isolation.class)
	public void AfterTheLastOne() {
		// “5”,”+”,"3","=" => “14”
	}
	

	@Test 
	@Category (Isolation.class)
	public void JustPressSomething() {
		// “6”,”+” => “6”	
	}
	
}
