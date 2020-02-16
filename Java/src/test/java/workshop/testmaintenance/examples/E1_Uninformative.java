package workshop.testmaintenance.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import workshop.testmaintenance.Calculator;
import workshop.testmaintenance.categories.Information;

public class E1_Uninformative {

	private static final String CALCULATOR_DISPLAY = "/calculator/display";
	private static final String CALCULATOR_PRESS = "/calculator/press";
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	// Not enough information on failure
	public void PressAndDisplay() {
		Calculator c = new Calculator();
		c.press("1");
		String result = c.getDisplay();
		assertTrue(result.equals("1"));
	}
	
	// Doesn't point to the problem when fails
	// Not enough information on failure
	// Name isn't informative either
	@Test
	public void SimpleCalcuation() throws Exception {
		mockMvc.perform(
				post(CALCULATOR_PRESS).param("key", "1"));
		mockMvc.perform(
				post(CALCULATOR_PRESS).param("key", "+"));
		mockMvc.perform(
				post(CALCULATOR_PRESS).param("key", "2"));
		mockMvc.perform(
				post(CALCULATOR_PRESS).param("key", "="));
		
		MvcResult result = mockMvc.perform(
				get(CALCULATOR_DISPLAY))
		.andExpect(status().isOk())
		.andReturn();

		assertEquals("3", 
				result.getResponse().getContentAsString());
	}
}
