package workshop.testmaintenance.examples;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

public class E2_Unreliable {

	private static final String CALCULATOR_PRESS = "/calculator/press";
	private static final String CALCULATOR_STORED_USER_GIL = "/calculator/stored?user=Gil";
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	// Flaky
	// Requires prior data
	public void CalculationsWithLastValueSaved() throws Exception {
		// Wait for server to answer
		MvcResult storedResult = 
				mockMvc.perform(
						get(CALCULATOR_STORED_USER_GIL))
						.andExpect(request().asyncStarted())
						.andReturn();
		String lastValue = (String) storedResult.getAsyncResult(1000);
		
		mockMvc.perform(
				post(CALCULATOR_PRESS).param("key", lastValue));
		mockMvc.perform(
				post(CALCULATOR_PRESS).param("key", "+"));
		mockMvc.perform(
				post(CALCULATOR_PRESS).param("key", "3"));
		mockMvc.perform(
				post(CALCULATOR_PRESS).param("key", "="));
		mockMvc.perform(get("/calculator/display"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.display").value("3"))
		.andReturn();		
	}

}
