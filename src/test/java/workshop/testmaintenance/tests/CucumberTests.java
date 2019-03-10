package workshop.testmaintenance.tests;

import org.junit.Test;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue="workshop.testmaintenance.tests")
public class CucumberTests {

	@Test
	public void itLoads() {
		
	}
}
