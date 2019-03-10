package workshop.testmaintenance.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, 
		features = "src/test/resources", 
		glue="workshop.testmaintenance.tests",
		monochrome = true)
public class CucumberTests {

}
