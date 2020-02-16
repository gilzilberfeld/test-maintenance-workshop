using System;
using Xunit.Gherkin.Quick;

namespace MaintenanceWorkshopTests
{
    [FeatureFile("GherkinTests.feature")]
    public class CalculatorSteps
    {
        [Given(@"A reset calculator")]
        public void GivenAResetCalculator()
        {
            throw new Exception();
        }
        
        [When(@"The user presses ""(.*)""")]
        public void WhenTheUserPresses(int p0)
        {
            throw new Exception();
        }
        [Then(@"The calculator displays ""(.*)""")]
        public void ThenTheCalculatorDisplays(int p0)
        {
            throw new Exception();
        }
    }
}
