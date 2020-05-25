using System;
using System.Collections.Generic;
using ExperimentsData.Models.DTO;
using ExperimentsData.Services;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace ExperimentsData.Controllers
{
    [EnableCors("CorsPolicy")]

    public class SampleController : ControllerBase
    {
        private readonly ILogger<SampleController> _logger;
        private readonly ISampleService _service;

        public SampleController(ISampleService service, ILogger<SampleController> logger)
        {
            _service = service;
            _logger = logger;
        }


        [HttpGet]
        [Route("/datasets/{dataset_guid}/samples")]
        public List<SampleListDTO> Get([FromRoute(Name = "dataset_guid")] Guid datasetGuid)
        {
            return _service.getAll(datasetGuid);
                
        }
        [HttpPost]
        [Route("/datasets/{dataset_guid}/samples")]
        public SampleRegisterDTO Create([FromRoute(Name = "dataset_guid")] Guid datasetGuid,[FromBody] SampleRegisterDTO registerDto)
        {
            return _service.Create(datasetGuid,registerDto);
        }
        
        [HttpGet]
        [Route("samples/{guid}")]
        public SampleRegisterDTO GetById([FromRoute(Name = "guid")] Guid sampleId)
        {
            return _service.getById(sampleId);
                
        }
    }
}