using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace ExperimentsData.Controllers
{
    [EnableCors("CorsPolicy")]
    public class ExperimentRoundController : ControllerBase
    {
        private readonly ILogger<ExperimentRoundController> _logger;
        
    }
}