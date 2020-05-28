using System;
using System.Collections.Generic;
using ExperimentsData.Models.DTO;
using ExperimentsData.Services;
using ExperimentsData.Services.Impl;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace ExperimentsData.Controllers
{
    [EnableCors("CorsPolicy")]

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
        
        [HttpGet]
        [Route("/datasets/name/{name}")]
        public DatasetListDTO GetByName(string name)
        {
            return _service.getByName(name);
                
        }

        [HttpPost]
        [Route("/datasets")]
        public DatasetRegisterDTO Create([FromBody] DatasetRegisterDTO registerDto)
        {
            return _service.Create(registerDto);
        }
        
        [HttpGet]
        [Route("/datasets/{guid}/download")]
        public FileContentResult DownloadFileById(Guid guid)
        {
            byte[] result = _service.DownloadFileById(guid); 
            return File(result, "text/plain", "file.txt");
        }
        
        [HttpDelete]
        [Route("/datasets/{guid}")]
        public DatasetRegisterDTO DeleteById(Guid guid)
        {
            return _service.deleteById(guid);
                
        }
    }
}