using System;
using System.Collections.Generic;
using System.Linq;
using AutoMapper;
using ExperimentsData.Models;
using ExperimentsData.Models.DAO;
using ExperimentsData.Models.DTO;
using ExperimentsData.Repositories;
using Microsoft.EntityFrameworkCore;

namespace ExperimentsData.Services
{
    
    public class DatasetService : IDatasetService
    {
        private IDatasetRepository _repository;
        private IMapper _mapper;

        public DatasetService(IDatasetRepository repository, IMapper mapper)
        {
            _repository = repository;
            _mapper = mapper;
        }

        public List<DatasetListDTO> getAll()
        {
            List<DatasetGroupedEntity> result = _repository.GetAllGrouped();
            return _mapper.Map<List<DatasetListDTO>>(result);
        }

        public DatasetEntity Create(DatasetEntity datasetEntity)
        {
            _repository.Save(datasetEntity);
            return datasetEntity;
        }

        public DatasetRegisterDTO getById(Guid guid)
        {
            return _mapper.Map<DatasetRegisterDTO>( _repository.GetById(guid));
        }
    }
}