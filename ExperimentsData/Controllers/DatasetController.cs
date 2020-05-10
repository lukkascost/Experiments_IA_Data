using System;
using System.Collections.Generic;
using System.Linq;
using ExperimentsData.Models;
using ExperimentsData.Models.DAO;
using ExperimentsData.Models.DTO;
using ExperimentsData.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;

namespace ExperimentsData.Controllers
{
    public class DatasetController : ControllerBase
    {
        
        private readonly ILogger<DatasetController> _logger;
        private readonly IDatasetService _service;
        

        public DatasetController(ILogger<DatasetController> logger, IDatasetService service)
        {
            _logger = logger;
            _service = service;
        }
        
        [HttpGet]
        [Route("/datasets")]
        public List<DatasetListDTO> Get()
        {
            return _service.getAll();
                
        }
        
         
        [HttpGet]
        [Route("/datasets/{guid}")]
        public DatasetRegisterDTO GetById(Guid guid)
        {
            return _service.getById(guid);
                
        }

        [HttpPost]
        [Route("/datasets")]
        public DatasetRegisterDTO Create([FromBody] DatasetRegisterDTO registerDto)
        {
            return _service.Create(registerDto);
        }
    }
}