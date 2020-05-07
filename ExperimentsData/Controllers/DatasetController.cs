using System;
using System.Collections.Generic;
using System.Linq;
using ExperimentsData.Models;
using ExperimentsData.Models.DAO;
using ExperimentsData.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;

namespace ExperimentsData.Controllers
{
    [Route("[controller]s")]
    public class DatasetController : ControllerBase
    {
        
        private readonly ILogger<DatasetController> _logger;
        private readonly IDatasetService _service;
        private readonly DataContext _repository;

        public DatasetController(ILogger<DatasetController> logger, IDatasetService service, DataContext repository)
        {
            _logger = logger;
            _service = service;
            _repository = repository;
        }
        
        [HttpGet]
        [Route("")]
        public List<DatasetEntity> Get()
        {
            return _repository.Datasets.ToList();
        }

        [HttpPost]
        public DatasetEntity Create([FromBody] DatasetEntity datasetEntity)
        {
            _repository.Datasets.Add(datasetEntity);
            _repository.Entry(datasetEntity).State = EntityState.Added;
            _repository.SaveChanges();
            return datasetEntity;
        }
    }
}