using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace CalculatorServer.Controllers
{
    [Route("calculator/")]
    [ApiController]
    public class CalculatorController : ControllerBase
    {
        private readonly ICalculator calculator;

        public CalculatorController(ICalculator calculator)
        {
                this.calculator = calculator;
        }

        [HttpGet("display")]
        public ActionResult<IEnumerable<string>> Get_Display()
        {
            return Content(calculator.Display);
        }

        [HttpPost("press")]
        public void Post_Press([FromQuery] string key)
        {
            calculator.Press(key);
        }
        
        [HttpPost("reset")]
        public void Post_Reset()
        {
            Calculator.Reset();
        }

        [HttpPost("restore")]
        public void Post_Restore([FromQuery] string user)
        {
            calculator.GetLastValueFor(user);
        }

        
        // GET api/values/5
        [HttpGet("{id}")]
        public ActionResult<string> Get(int id)
        {
            return "value";
        }


        // PUT api/values/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] string value)
        {

        }

        // DELETE api/values/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}
