using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.AspNetCore.Mvc.Testing;
using Microsoft.AspNetCore.TestHost;
using System.Net.Http;
using System.Threading.Tasks;
using System;
using System.Net;
using System.Web;
using CalculatorServer;
using Microsoft.EntityFrameworkCore;
using System.Linq;
using ApprovalTests.Reporters;
using ApprovalTests;

namespace MaintenanceWorkshopTests
{
    
    [TestClass]
    public class IntegrationTests
    {
        private static TestServerFactory factory;
        private static HttpClient client;


        [ClassInitialize]
        public static void Setup(TestContext tc)
        {
            factory = new TestServerFactory();
            client = factory.CreateClient();
        }


        [ClassCleanup]
        public static void TearDown()
        {
            factory.Dispose();
            client.Dispose();
        }

        [TestInitialize]
        public async Task Setup()
        {
            await client.PostAsync("calculator/reset", null);

        }

        [TestMethod]
        [TestCategory("Verification")]
        public void Verification()
        {
            Assert.IsNotNull(client);
            Assert.AreEqual("http://localhost/", client.BaseAddress.ToString());
        }

        [TestMethod]
        public async Task SimpleGet()
        {
            var response = await client.GetAsync("calculator/display");
            Assert.AreEqual("0", response.Content.ReadAsStringAsync().Result);
            Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
        }


        [TestMethod]
        public async Task SimplePostAndGet()
        {
            await client.PostAsync("calculator/press?key=1", null);
            var response = await client.GetAsync("calculator/display");
            Assert.AreEqual("1", response.Content.ReadAsStringAsync().Result);
        }

        [TestCategory("Information")]
        [TestMethod]
        public async Task SimpleCalcuation()
        {
            string url;

            url = "calculator/press?key=" + GetEncodedKey("1");
            await client.PostAsync(url, null);
            var response = await client.GetAsync("calculator/display");
            Assert.AreEqual("1", response.Content.ReadAsStringAsync().Result);

            url = "calculator/press?key=" + GetEncodedKey("+");
            response = await client.PostAsync(url, null);
            response = await client.GetAsync("calculator/display");
            Assert.AreEqual("1", response.Content.ReadAsStringAsync().Result);

            url = "calculator/press?key=" + GetEncodedKey("2");
            await client.PostAsync(url, null);
            response = await client.GetAsync("calculator/display");
            Assert.AreEqual("2", response.Content.ReadAsStringAsync().Result);

            url = "calculator/press?key=" + GetEncodedKey("=");
            await client.PostAsync(url, null);
            response = await client.GetAsync("calculator/display");
            Assert.AreEqual("3", response.Content.ReadAsStringAsync().Result);
        }

        [TestMethod]
        [TestCategory("Isolation")]
        public async Task RestoreByUser()
        {
            var url = "calculator/restore?user=Gil";
            await client.PostAsync(url, null);
            var response = await client.GetAsync("calculator/display");
            Assert.AreEqual("2", response.Content.ReadAsStringAsync().Result);
        }

        [Ignore]
        [TestMethod]
        [UseReporter(typeof(DiffReporter))]
        public void GetWithApprovals()
        {
            string result = GetSync("calculator/display");
            Approvals.Verify(result);

        }

        private static string GetSync(string url)
        {
            Task<HttpResponseMessage> task =
                            Task.Run<HttpResponseMessage>(async () => await client.GetAsync(url));
            return task.Result.Content.ReadAsStringAsync().Result;
        }

        private static string GetEncodedKey(string sign)
        {
            return HttpUtility.UrlEncode(sign);
        }
    }
}
