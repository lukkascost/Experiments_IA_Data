using System;
using System.Collections.Generic;
using AutoMapper;
using ExperimentsData.Models.DAO;
using ExperimentsData.Models.DTO;
using ExperimentsData.Repositories;

namespace ExperimentsData.Services.Impl
{
    public class SampleService :ISampleService
    {
        private ISampleRepository _repository;
        private IDatasetRepository _datasetRepository;
        private IMapper _mapper;

        public SampleService(ISampleRepository repository, IMapper mapper, IDatasetRepository datasetRepository)
        {
            _repository = repository;
            _mapper = mapper;
            _datasetRepository = datasetRepository;
        }

        public List<SampleListDTO> getAll(Guid datasetGuid)
        {
            if(_datasetRepository.GetById(datasetGuid) == null) throw new ArgumentException("THIS DATASET DOES NOT EXISTS.");
            List<SampleGroupedEntity> result = _repository.GetAllGrouped(datasetGuid);
            return _mapper.Map<List<SampleListDTO>>(result);
        }

        public SampleRegisterDTO Create(Guid datasetGuid, SampleRegisterDTO registerDto)
        {
            var entity = _mapper.Map<SampleEntity>(registerDto);
            entity.DatasetEntity = _datasetRepository.GetById(datasetGuid);
            if(entity.DatasetEntity == null) throw new ArgumentException("THIS DATASET DOES NOT EXISTS.");
            entity.DatasetEntityId = entity.DatasetEntity.Id;
            _repository.Save(entity);
            return registerDto;
        }

        public SampleRegisterDTO getById(Guid sampleId)
        {
            return _mapper.Map<SampleRegisterDTO>( _repository.GetById(sampleId));

        }
    }
}