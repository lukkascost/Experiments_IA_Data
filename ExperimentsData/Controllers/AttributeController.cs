using System;
using System.Collections.Generic;
using ExperimentsData.Models.DTO;
using ExperimentsData.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace ExperimentsData.Controllers
{
    public class AttributeController
    {
        
        private readonly ILogger<DatasetController> _logger;
        private readonly IAttributeService _service;

        public AttributeController(ILogger<DatasetController> logger, IAttributeService service)
        {
            _logger = logger;
            _service = service;
        }
        
        [HttpGet]
        [Route("samples/{guid}/attributes")]
        public List<AttributeRegisterDto> GetAll([FromRoute(Name = "guid")] Guid sampleId)
        {
            return _service.getAll(sampleId);
        }
         
        [HttpPost]
        [Route("samples/{guid}/attributes")]
        public AttributeRegisterDto Create([FromRoute(Name = "guid")] Guid sampleId, [FromBody] AttributeRegisterDto registerDto)
        {
            return _service.create(sampleId, registerDto);
        }

    }
}