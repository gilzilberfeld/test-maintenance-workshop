package workshop.testmaintenance.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import workshop.testmaintenance.Calculator;
import workshop.testmaintenance.CalculatorController;

@Configuration
@Import(JpaConfiguration.class)
public class CalculatorTestConfiguration {
	
	@Bean CalculatorController controller() {
		return new CalculatorController();
	}
	
	@Bean Calculator calculator() {
		return new Calculator();
	}
}

